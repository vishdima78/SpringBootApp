package vish.dima.crud.dao;

public class PersonDAO {
}

//    private final SessionFactory sessionFactory;
//
//    @Autowired
//    public PersonDAO(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//    @Transactional(readOnly = true)
//    public List<Person> index() {
//        Session session = sessionFactory.getCurrentSession();
//
//        return session.createQuery("select p from Person p", Person.class).getResultList();
//    }
//
//    @Transactional
//    public Optional<Person> show(String name) {
//        Session session = sessionFactory.getCurrentSession();
//        return session.createQuery("select p from Person p where  name=:name").stream().findAny();
//        return jdbcTemplate.query("select * from Person WHERE name=?", new Object[]{name},
//                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
//    }
//
//    @Transactional(readOnly = true)
//    public Person show(int id) {
//        Session session = sessionFactory.getCurrentSession();
//
//        return session.get(Person.class, id);
//
//
//    }
//
//    @Transactional
//    public void save(Person person) {
//        Session session = sessionFactory.getCurrentSession();
//        session.save(person);
//    }
//
//    @Transactional
//    public void update(int id, Person updatedPerson) {
//        Session session = sessionFactory.getCurrentSession();
//
//        Person toBeUpd = session.get(Person.class, id);
//        toBeUpd.setName(updatedPerson.getName());
//        toBeUpd.setYear(updatedPerson.getYear());
//
//    }
//
//    @Transactional
//    public void delete(int id) {
//        Session session = sessionFactory.getCurrentSession();
//        session.remove(session.get(Person.class, id));
//
//    }
//
//    @Transactional
//    public List<Book> showAllBookForId(int id) {
//       Session session = sessionFactory.getCurrentSession();
//       return session.createQuery("select p from Book p  where person_id=:id", Book.class).getResultList();
//        return jdbcTemplate.query("select * from book where person_id =?",
//                new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));


//
//    public void testMultipleUpdate() {
//        List<Person> people = create1000people();
//        long before = System.currentTimeMillis();
//        for (Person person : people) {
//            jdbcTemplate.update("INSERT INTO Person VALUES (1,?,?)", person.getFio(),
//                    person.getBirthdayYear());
//
//        }
//        long after = System.currentTimeMillis();
//        System.out.println("Time: " + (after - before));
//    }
//
//    public void testDeleteAll() {
//        String truncateSQL = "TRUNCATE TABLE " + "Person" + " CASCADE";
//        jdbcTemplate.execute(truncateSQL);
//
//    }
//
//    public void testBatchUpdate() {
//        List<Person> people = create1000people();
//        long before = System.currentTimeMillis();
//        jdbcTemplate.batchUpdate("INSERT INTO Person(name,age,email) VALUES (?,?,?)",
//                new BatchPreparedStatementSetter() {
//                    @Override
//                    public void setValues(PreparedStatement ps, int i) throws SQLException {
//                        ps.setString(1, people.get(i).getFio());
//                        ps.setInt(2, people.get(i).getBirthdayYear());
//               ps.setString(3,people.get(i).getEmail());
//                    }
//
//                    @Override
//                    public int getBatchSize() {
//                        return people.size();
//                    }
//                });
//        long after = System.currentTimeMillis();
//        System.out.println("Time: " + (after - before));
//
//    }
//
//    private List<Person> create1000people() {
//        List<Person> people = new ArrayList<>();
//        for (int i = 0; i < 1000; i++) {
//            people.add(new Person(i, "Name" + i, 30));
//        }
//        return people;
//    }

