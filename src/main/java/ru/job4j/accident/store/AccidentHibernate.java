package ru.job4j.accident.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

@Repository
public class AccidentHibernate implements Store {
    private static final Logger LOG = LoggerFactory.getLogger(AccidentHibernate.class);
    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public Collection<Accident> findAll() {
        try (Session session = sf.openSession()) {
            return session.createQuery(
                    "select distinct a from Accident a join fetch a.type join fetch a.rules"
            ).list();
        }
    }

    @Override
    public Accident findById(int id) {
        return this.tx(session -> session.get(Accident.class, id));
    }

    @Override
    public void addOrUpdate(Accident accident) {
        if (accident.getId() == 0) {
            saveAccident(accident);
        } else {
            updateAccident(accident);
        }
    }

    @Override
    public void delete(int id) {
        this.makeTransaction(session -> {
            Accident accident = new Accident();
            accident.setId(id);
            session.delete(accident);
        });
    }

    @Override
    public Collection<AccidentType> findAllTypes() {
        try (Session session = sf.openSession()) {
            return session.createQuery(
                    "from AccidentType"
            ).list();
        }
    }

    @Override
    public AccidentType findTypeById(int id) {
        return this.tx(session -> session.get(AccidentType.class, id));
    }

    @Override
    public Collection<Rule> findAllRules() {
        try (Session session = sf.openSession()) {
            return session.createQuery(
                    "from Rule "
            ).list();
        }
    }

    @Override
    public Rule findRuleById(int id) {
        return this.tx(session -> session.get(Rule.class, id));
    }

    @Override
    public Set<Rule> findRulesByIds(String[] ids) {
        Set<Rule> result = new HashSet<>();
        for (String id : ids) {
            result.add(findRuleById(Integer.parseInt(id)));
        }
        return result;
    }

    private void saveAccident(Accident accident) {
        this.makeTransaction(session -> session.save(accident));
    }

    private void updateAccident(Accident accident) {
        this.makeTransaction(session -> session.update(accident));
    }

    private <T> T tx(Function<Session, T> command) {
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        T rsl = null;
        try {
            rsl = command.apply(session);
            tx.commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOG.error("Error with transaction", e);
        } finally {
            session.close();
        }
        return rsl;
    }

    private void makeTransaction(Consumer<Session> command) {
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        try {
            command.accept(session);
            tx.commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOG.error("Error with transaction", e);
        } finally {
            session.close();
        }
    }
}
