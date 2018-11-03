import org.hibernate.*;
import org.hibernate.query.Query;
import org.hibernate.cfg.Configuration;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Main {

    private static SessionFactory ourSessionFactory;
    static {
        try { // Create the SessionFactory from hibernate.cfg.xml
            ourSessionFactory =  new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) { throw new ExceptionInInitializerError(ex); }
    }

    private static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession(); //return opened session
    }
    //---------------------------------------------------------------------------
    public static void main(final String[] args) throws Exception {
        // get opened session
        Session session = getSession();
        try {
            menu(session);
            System.out.println("Finish work!");
        }catch (Throwable e) {
            System.out.println(e.getMessage());
        }
        finally { session.close(); System.exit(0); }
    }

    public static void menu(Session session) {
        Scanner scan = new Scanner(System.in);
        System.out.println("SELECT ONE OF THE FOLLOWINGS:");
        System.out.println("1.READ DATA FROM THE TABLE");
        System.out.println("2.INPUT DATA INTO THE TABLE");
        System.out.println("3.EDIT DATA IN THE TABLE");
        System.out.println("4.DROP DATA IN THE TABLE");

        int result = scan.nextInt();

        if (result == 1) {
            System.out.println("CHOOSE TABLE:\n" +
                    "1.GOOD" +
                    "2.PROVIDER" +
                    "3.INVOICE");
            Scanner input = new Scanner(System.in);
            readData(input.nextInt(), session);
        } else if (result == 2) {
            System.out.println("CHOOSE TABLE:\n" +
                    "1.GOOD" +
                    "2.PROVIDER" +
                    "3.INVOICE" +
                    "4.GOOD_PROVIDER");
            Scanner input = new Scanner(System.in);
            insertData(input.nextInt(), session);
        } else if (result == 3) {
            System.out.println("CHOOSE TABLE:\n" +
                    "1.GOOD" +
                    "2.PROVIDER" +
                    "3.INVOICE");
            Scanner input = new Scanner(System.in);
            updateData(input.nextInt(), session);

        } else if (result == 4) {
            Scanner input = new Scanner(System.in);
            System.out.println("CHOOSE TABLE:\n" +
                    "1.GOOD" +
                    "2.PROVIDER" +
                    "3.INVOICE");
            deleteData(input.nextInt(), session);

        } else {
            System.out.println("YOU HAVE ENTERED INCORRECT DATA! PLEASE, TRY AGAIN...");
            menu(session);
        }
    }

    public static void readData(int tableNum, Session session) {
        switch (tableNum) {
            case 1:
                readGoodTable(session);
                readGoodJoinProvider(session);break;
            case 2: readProviderTable(session);
                readProviderJoinGood(session); break;
            case 3:
                readInvoiceTable(session); break;
        }
        menu(session);
    }

    public static void insertData(int tableNum, Session session) {
        switch (tableNum) {
            case 1:
                insertGood(session); break;
            case 2:
                insertProvider(session); break;
            case 3:
                insertInvoice(session); break;
            case 4:
                insertGoodProvider(session); break;
        }
        menu(session);
    }

    public static void updateData(int tableNum, Session session) {
        switch (tableNum) {
            case 1:
                updateGood(session); break;
            case 2:
                updateProvider(session); break;
            case 3:
                updateInvoice(session); break;
        }
        menu(session);
    }

    public static void deleteData(int tableNum, Session session) {
        switch (tableNum) {
            case 1:
                deleteGood(session); break;
            case 2:
                deleteProvider(session); break;
            case 3:
                deleteInvoice(session); break;
        }
        menu(session);
    }






//Read Good
    public static void readGoodTable(Session session) {
        Query query = session.createQuery("from " + "Good");
        System.out.format("\nTable Good --------------------\n");
        System.out.format("%5s %20s %5s %5s\n", "Code", "Brand", "Price", "Count");
        for (Object obj : query.list()) {
            Good good = (Good) obj;
            System.out.format("%5s %20s %f %3d\n", good.getCode(),
                    good.getBrand(), good.getPrice(), good.getCount());
        }
    }


//Read Good join Provider
    public static void readGoodJoinProvider(Session session) {
        Query query = session.createQuery("from " + "Good");
        System.out.format("\nTable Good Join Provider--------------------\n");
        System.out.format("%5s %20s %20s\n", "Code", "Brand", "List of Providers");
        for (Object obj : query.list()) {
            Good good = (Good) obj;
            System.out.format("%5s %20s\n", good.getCode(), good.getBrand());
            for (Object obj2 : good.getProviders()) {
                Provider provider = (Provider) obj2;
                System.out.format("%25s %20s\n", " ", provider.getName());
            }
        }
    }


//Read Provider
    public static void readProviderTable(Session session) {
        Query query = session.createQuery("from " + "Provider");
        System.out.format("\nTable Provider --------------------\n");
        System.out.format("%3s %20s %30s %13s\n", "ID", "Name", "Director", "Phone");
        for (Object obj : query.list()) {
            Provider provider = (Provider) obj;
            System.out.format("%3d %20s %30s %13s\n", provider.getId(), provider.getName(), provider.getDirector(), provider.getPhone());
        }
    }

//Read Provider join Good
    public static void readProviderJoinGood(Session session) {
        Query query = session.createQuery("from " + "Provider");
        System.out.format("\nTable Provider join Good--------------------\n");
        System.out.format("%3s %20s %20s\n", "Code", "Brand", "List of Goods");
        for (Object obj : query.list()) {
            Provider provider = (Provider) obj;
            System.out.format("%3s %20s\n", provider.getId(), provider.getName());
            for (Object obj2 : provider.getGoods()) {
                Good good = (Good) obj2;
                System.out.format("%23s %20s\n", " ", good.getCode());
            }
        }
    }


//Read Invoice
    public static void readInvoiceTable(Session session) {
        Query query = session.createQuery("from " + "Invoice");
        System.out.format("\nTable Invoice --------------------\n");
        System.out.format("%5s %10s %20s\n", "Number", "Date", "Provider");
        for (Object obj : query.list()) {
            Invoice invoice = (Invoice) obj;
            System.out.format("%5s %10s %30s\n", invoice.getNumber(), invoice.getDate(), invoice.getProviderByProvider().getName());
        }
    }



    private static void insertInvoice(Session session){
        Scanner input = new Scanner(System.in);
        System.out.println("Input a new name invoice:\nnumber: ");
        String number = input.next();
        System.out.println("date: ");
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date myDate = formatter.parse(input.next());
            java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
            readProviderTable(session);
            System.out.println("\nprovider id: ");
            Query query = session.createQuery("from " + "Provider");
            Provider provider = (Provider) query.list().get(input.nextInt());
            Invoice invoice = new Invoice(number, sqlDate, provider);
            session.beginTransaction();
            session.save(invoice);
            session.getTransaction().commit();
        } catch (ParseException e) {
            System.out.println("ParseException: " + e.getMessage());
        }

        System.out.println("end insert invoice");
    }

    private static void insertProvider(Session session){
        Scanner input = new Scanner(System.in);
        System.out.println("Input new Provider Name: ");
        String name_new = input.next();
        System.out.println("Input new Provider director: ");
        String director_new = input.next();
        System.out.println("Input the Provider's phone: ");
        String phone = input.next();

        session.beginTransaction();
        Provider provider = new Provider(name_new, director_new, phone);
        session.save(provider);
        session.getTransaction().commit();
        System.out.println("end insert provider");
    }

    private static void insertGood(Session session){
        Scanner input = new Scanner(System.in);
        System.out.println("Input new Good code: ");
        String code = input.next();
        System.out.println("Input new Good brand: ");
        String brand = input.next();
        System.out.println("Input the Good's price: ");
        double price = input.nextFloat();
        System.out.println("Input the Good's count: ");
        int count = input.nextInt();

        session.beginTransaction();
        Good good = new Good(code, brand, price, count);
        session.save(good);
        session.getTransaction().commit();
        System.out.println("end insert good");
    }

    private static void insertGoodProvider(Session session){
        Scanner input = new Scanner(System.in);
        readGoodTable(session);
        System.out.println("Input Good code: ");
        String code = input.next();
        readProviderTable(session);
        System.out.println("Input Provider id: ");
        int id = input.nextInt();

        session.beginTransaction();
        GoodProvider goodProvider = new GoodProvider(code, id);
        session.save(goodProvider);
        session.getTransaction().commit();
        System.out.println("end insert good_provider");
    }

    private static void updateInvoice(Session session){
        Scanner input = new Scanner(System.in);
        readInvoiceTable(session);
        System.out.println("\nInput a invoice number: ");
        String oldNumber = input.next();
        System.out.println("Input new date: ");
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date myDate = formatter.parse(input.next());
            java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
            readProviderTable(session);
            System.out.println("\nInput new provider id: ");
            int providerId = input.nextInt();
            Invoice invoice = (Invoice) session.load( Invoice.class, oldNumber);
            if(invoice!=null){
                session.beginTransaction();
                Query query1 = session.createQuery("update Invoice set data=:code2, provider_id=:code3  where number=:code4");
                query1.setParameter("code2", sqlDate);
                query1.setParameter("code3", providerId);
                query1.setParameter("code4", oldNumber);
                int result = query1.executeUpdate();
                session.getTransaction().commit();
                System.out.println("end update invoice: "+ result);
            }
            else System.out.println("There is no the invoice");
        } catch (ParseException e) {
            System.out.println("ParseException: " + e.getMessage());
        }
    }

    private static void updateProvider(Session session){
        Scanner input = new Scanner(System.in);
        readProviderTable(session);
        System.out.println("\nInput a provider id: ");
        int oldId = input.nextInt();
        System.out.println("Input new name: ");
        String name = input.next();
        System.out.println("Input new director: ");
        String director = input.next();
        System.out.println("Input new phone: ");
        String phone = input.next();
        Provider provider = (Provider) session.load( Provider.class, oldId);
        if(provider!=null){
            session.beginTransaction();
            Query query1 = session.createQuery("update Provider set name=:code2, director=:code3, phone=:code4  where id=:code5");
            query1.setParameter("code2", name);
            query1.setParameter("code3", director);
            query1.setParameter("code4", phone);
            query1.setParameter("code5", oldId);
            int result = query1.executeUpdate();
            session.getTransaction().commit();
            System.out.println("end update provider: "+ result);
        }
        else System.out.println("There is no the provider");
    }

    private static void updateGood(Session session){
        Scanner input = new Scanner(System.in);
        readGoodTable(session);
        System.out.println("\nInput a good code: ");
        String oldCode = input.next();
        System.out.println("Input new brand: ");
        String brand = input.next();
        System.out.println("Input new price: ");
        double price = input.nextFloat();
        System.out.println("Input new count: ");
        int count = input.nextInt();
        Good good = (Good) session.load( Good.class, oldCode);
        if(good!=null){
            session.beginTransaction();
            Query query1 = session.createQuery("update Good set brand=:code2, price=:code3, count=:code4  where code=:code5");
            query1.setParameter("code2", brand);
            query1.setParameter("code3", price);
            query1.setParameter("code4", count);
            query1.setParameter("code5", oldCode);
            int result = query1.executeUpdate();
            session.getTransaction().commit();
            System.out.println("end update good: "+ result);
        }
        else System.out.println("There is no the good");
    }

    private static void deleteProvider(Session session) {
        Scanner input = new Scanner(System.in);
        readProviderTable(session);
        System.out.println("\nInput a id of provider you want to delete: ");
        int id = input.nextInt();


        Query query = session.createQuery("from " + "Provider where id = :code");
        query.setParameter("code", id);

        if (!query.list().isEmpty()) {
            session.beginTransaction();

            Provider entity = session.load(Provider.class, id);
            session.delete(entity);
            session.getTransaction().commit();

            System.out.println("The row was successfully deleted");
        } else {
            System.out.println("There is no such a provider");
        }
    }

    private static void deleteGood(Session session) {
        Scanner input = new Scanner(System.in);
        readGoodTable(session);
        System.out.println("\nInput a code of good you want to delete: ");
        String code = input.next();


        Query query = session.createQuery("from " + "Good where code = :code");
        query.setParameter("code", code);

        if (!query.list().isEmpty()) {
            session.beginTransaction();

            Good entity = session.load(Good.class, code);
            session.delete(entity);
            session.getTransaction().commit();

            System.out.println("The row was successfully deleted");
        } else {
            System.out.println("There is no such a good");
        }
    }

    private static void deleteInvoice(Session session) {
        Scanner input = new Scanner(System.in);
        readInvoiceTable(session);
        System.out.println("\nInput a code of invoice you want to delete: ");
        String code = input.next();


        Query query = session.createQuery("from " + "Invoice where number = :code");
        query.setParameter("code", code);

        if (!query.list().isEmpty()) {
            session.beginTransaction();

            Invoice entity = session.load(Invoice.class, code);
            session.delete(entity);
            session.getTransaction().commit();

            System.out.println("The row was successfully deleted");
        } else {
            System.out.println("There is no such an invoice");
        }
    }

}
