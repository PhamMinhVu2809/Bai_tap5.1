public class Bai2 {
    public static void main(String[] args) {
        OfficeEmployee office = new OfficeEmployee("An");
        TechnicalEmployee tech = new TechnicalEmployee("Bình");
        SalesEmployee sales = new SalesEmployee("Chi");
        TechLeadEmployee lead = new TechLeadEmployee("Dũng");

        System.out.println("=== Chức năng riêng của từng nhân viên ===");
        office.sendEmail("hr@congty.vn", "Gửi bảng chấm công tháng 10");
        tech.writeCode("Java");
        tech.sendEmail("pm@congty.vn", "Đã hoàn thành module đăng nhập");
        sales.sell("laptop", 5);
        sales.sendEmail("khachhang@gmail.com", "Báo giá sản phẩm");
        lead.writeCode("Java");
        lead.sell("phần mềm quản lý", 1);

        System.out.println("\n=== Thông báo chung qua EmailSender ===");
        EmailSender[] senders = { office, tech, sales, lead };
        for (EmailSender s : senders) {
            s.sendEmail("all@congty.vn", "Họp toàn công ty lúc 9h");
        }

        System.out.println("\n=== Những ai biết lập trình (Programmer) ===");
        Programmer[] programmers = { tech, lead };
        for (Programmer p : programmers) {
            p.writeCode("Python");
        }

        System.out.println("\n=== Những ai bán hàng (Salesperson) ===");
        Salesperson[] sellers = { sales, lead };
        for (Salesperson sp : sellers) {
            sp.sell("gói bảo hành", 2);
        }
    }
}

interface EmailSender {
    void sendEmail(String to, String content);
}

interface Programmer {
    void writeCode(String language);
}

interface Salesperson {
    void sell(String product, int quantity);
}

abstract class Employee {
    protected String name;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    public abstract String getRole();

    @Override
    public String toString() {
        return getRole() + " " + name;
    }
}

class OfficeEmployee extends Employee implements EmailSender {
    public OfficeEmployee(String name) { super(name); }

    @Override
    public String getRole() { return "NV văn phòng"; }

    @Override
    public void sendEmail(String to, String content) {
        System.out.println(this + " gửi email tới " + to + ": \"" + content + "\"");
    }
}

class TechnicalEmployee extends Employee implements Programmer, EmailSender {
    public TechnicalEmployee(String name) { super(name); }

    @Override
    public String getRole() { return "NV kỹ thuật"; }

    @Override
    public void writeCode(String language) {
        System.out.println(this + " đang lập trình bằng " + language);
    }

    @Override
    public void sendEmail(String to, String content) {
        System.out.println(this + " gửi email tới " + to + ": \"" + content + "\"");
    }
}

class SalesEmployee extends Employee implements Salesperson, EmailSender {
    public SalesEmployee(String name) { super(name); }

    @Override
    public String getRole() { return "NV bán hàng"; }

    @Override
    public void sell(String product, int quantity) {
        System.out.println(this + " bán " + quantity + " " + product);
    }

    @Override
    public void sendEmail(String to, String content) {
        System.out.println(this + " gửi email tới " + to + ": \"" + content + "\"");
    }
}

class TechLeadEmployee extends Employee implements Programmer, Salesperson, EmailSender {
    public TechLeadEmployee(String name) { super(name); }

    @Override
    public String getRole() { return "Trưởng nhóm"; }

    @Override
    public void writeCode(String language) {
        System.out.println(this + " review và viết code " + language);
    }

    @Override
    public void sell(String product, int quantity) {
        System.out.println(this + " tư vấn kỹ thuật, chốt bán " + quantity + " " + product);
    }

    @Override
    public void sendEmail(String to, String content) {
        System.out.println(this + " gửi email tới " + to + ": \"" + content + "\"");
    }
}
