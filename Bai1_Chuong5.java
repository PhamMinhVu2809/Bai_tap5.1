public class Bai1 {
    public static void main(String[] args) {
        Employee[] employees = {
            new OfficeEmployee("An", 25, 22),
            new TechnicalEmployee("Bình", 30, 160, 15),
            new OfficeEmployee("Chi", 28, 20),
            new TechnicalEmployee("Dũng", 35, 180, 20)
        };

        double total = 0;
        for (Employee e : employees) {
            System.out.println(e);
            total += e.calculateSalary();
        }
        System.out.printf("Tổng quỹ lương: %.0f%n", total);
    }
}

abstract class Employee {
    protected String name;
    protected int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public abstract double calculateSalary();

    public String getName() { return name; }
    public int getAge() { return age; }

    @Override
    public String toString() {
        return String.format("%-10s | %-3d tuổi | Lương: %.0f", name, age, calculateSalary());
    }
}

class OfficeEmployee extends Employee {
    public static final double DAILY_WAGE = 100;

    private int workingDays;

    public OfficeEmployee(String name, int age, int workingDays) {
        super(name, age);
        this.workingDays = workingDays;
    }

    @Override
    public double calculateSalary() {
        return workingDays * DAILY_WAGE;
    }

    @Override
    public String toString() {
        return "[Văn phòng] " + super.toString() + " (" + workingDays + " ngày × " + (int) DAILY_WAGE + ")";
    }
}

class TechnicalEmployee extends Employee {
    private int workingHours;
    private double hourlyRate;

    public TechnicalEmployee(String name, int age, int workingHours, double hourlyRate) {
        super(name, age);
        this.workingHours = workingHours;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculateSalary() {
        return workingHours * hourlyRate;
    }

    @Override
    public String toString() {
        return "[Kỹ thuật]  " + super.toString() + " (" + workingHours + " giờ × " + (int) hourlyRate + ")";
    }
}
