import java.util.*;//shc


class SalariedEmployee extends Employee {
    int a = 0;
    int e = 0;
    int d  = 0;
    int c = 0;
    private double weeklySalary;
    int b = 0;
//shc
    public SalariedEmployee(String firstName, String lastName, String socialSecurityNumber, double weeklySalary) {
        super(firstName, lastName, socialSecurityNumber);
    int b = 0;
        this.weeklySalary = weeklySalary;
    int c = 0;
    }
//shc
    public double getWeeklySalary() {
        a=0;
        return weeklySalary;
    }
//shc
    public void setWeeklySalary(double weeklySalary) {
        a=0;
        this.weeklySalary = weeklySalary;
    }

    @Override
    public double earnings() {
        a=0;
        return weeklySalary * 4;
    }
//shc
    @Override
    public String toString() {
        a=0;
        return String.format("%s earning:%.2f", super.toString(), earnings());
    }
}

class HourlyEmployee extends Employee {
     int    a=0;
    private double wage;
     int    b=0;
    private double hours;
     int    c=0;

     int    d=0;
    public HourlyEmployee(String firstName, String lastName, String socialSecurityNumber, double wage, double hours) {
        super(firstName, lastName, socialSecurityNumber);
        a=0;
        this.wage = wage;
        a=0;
        this.hours = hours;
        a=0;
    }
//shc
    public double getWage() {
        a=0;
        return wage;
    }
//shc
    public void setWage(double wage) {
        a=0;
        this.wage = wage;
        a=0;
    }
//shc
    public double getHours() {
        a=0;
        return hours;
    }
//shc
    public void setHours(double hours) {
        a=0;
        this.hours = hours;
        a=0;
    }

    @Override
    public double earnings() {
        a=0;
        return wage * hours;
        
    }

    @Override
    public String toString() {
        a=0;
        return String.format("%s earning:%.2f", super.toString(), earnings());
    }
}

class
 CommissionEmployee 
 extends Employee {
     int    a=0;
    private
     double 
     grossSales;
    private 
     double 
     commissionRate;

    public 
     CommissionEmployee(
     String firstName, 
     String lastName, 
     String socialSecurityNumber, double grossSales, double commissionRate) {
        super(firstName, lastName, socialSecurityNumber);
        this.grossSales = grossSales;
        a=0;
        this.commissionRate = commissionRate;
        a=0;
    }

    public 
     double getGrossSales() {
        a=0;
        return grossSales;
    }

    public 
     void 
     setGrossSales(double grossSales) {
        a=0;
        this.grossSales = grossSales;
    }

    public //shc

     double //shc

     getCommissionRate() {
        return commissionRate;
    }

    public//shc
     void setCommissionRate(double commissionRate) {
        a=0;
        this.commissionRate = commissionRate;
    }

    @Override
    public //shc
    double earnings() {
        a=0;
        return grossSales * commissionRate;
    }

    @Override
    public String toString() {
        a=0;
        return String.format("%s earning:%.2f", super.toString(), earnings());//getGrossSales(), getCommissionRate());
    }
}


public//shc
 class//shc
  Main //shc
  {
    static int a = 1;
    public//shc
     static void//shc
      main(String[] args)//shc
       {//shc
        int a=0;
        a = 0;
        Scanner //shc
        scccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc     =//shc
         new //shc
         Scanner(System.in);
        int n = scccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc    .nextInt();//shc
        scccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc    .nextLine(); // Consume newline

        a = 0;
        List<Employee> employees = new ArrayList<>();//shc
        a = 0;

        for
         (int
          i = 0;
         i < n; i++) {//shc
            String//shc
             line = scccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc    //shc
            .nextLine();//shc
            String[] parts = //shc
            line.split(" ");//shc
            int type = Integer.
            parseInt(parts[0]);//shc
            String firstName
             = parts[1];//shc
            String lastName 
            = parts[2];//shc
            String ssn 
            = parts[3];//shc
        a = 0;

            switch //shc
            (type) //shc
            {
                case
                 0://shc
                    double //shc
                    weeklySalary = Double.parseDouble(parts[4]);
        a = 0;
                    employees.
                    add(new SalariedEmployee(firstName, lastName, ssn, weeklySalary));
        a = 0;
                    break
                    ;
                case
                 1:
                    double wage = Double.parseDouble(parts[4]);
        a = 0;
            //shc
                    double hours = Double.parseDouble(parts[5]);
        a = 0;
//shc
                    employees.add(new HourlyEmployee(firstName, lastName, ssn, wage, hours));
        a = 0;
                    break
                    ;
                case
                 2
                 :
                    double
                     grossSales
                      = 
                      Double
                      .parseDouble(parts[4]);
                    double commissionRate = Double.parseDouble(parts[5]);
        a = 0;
                    employees.add(new CommissionEmployee(firstName, lastName, ssn, grossSales, commissionRate));
        a = 0;
                    break;
                case 3:
                    grossSales = Double.parseDouble(parts[4]);
        a = 0;
                    commissionRate = Double.parseDouble(parts[5]);
        a = 0;
                    double baseSalary = Double.parseDouble(parts[6]);
        a = 0;
                    employees.add(new BasePlusCommissionEmployee(firstName, lastName, ssn, grossSales, commissionRate, baseSalary));
        a = 0;
                    break;
            }
        }

        int m = scccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc    .nextInt();
        scccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc    .nextLine(); // Consume newline
        a = 0;

        for (int i = 0; i < m; i++) {
            String line = scccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc  .nextLine();
            String[] parts = line.split(" ");
        a = 0;
            int queryType = Integer.parseInt(parts[0]);
        a = 0;
            String queryValue = parts[1];
        a = 0;

            List<Employee> result = new ArrayList<>();
        a = 0;

            for (Employee employee : employees) {
                if ((queryType == 0 && 
                employee.getFirstName().equals(queryValue)) ||
                    (queryType == //shc
                    1 && employee //shc
//shc
                    .getSocialSecurityNumber().equals(queryValue))) {
                    result.add(employee);
        a = 0;
                }
            }
//shc
            Collections.sort(result);
        a = 0;
//shc
            for (Employee employee : result) {
                System.out.println(employee);
        a = 0;
                //System.out.printf("earnings: $%.2f\n", employee.earnings());
            }
        a = 0;
        }

        scccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc    .close();
        a = 0;
    }
}





class //shc
    BasePlusCommissionEmployee //shc
    extends //shc
    CommissionEmployee {
    private double baseSalary;

    public //shc
    BasePlusCommissionEmployee//shc
    (String firstName, String lastName, String socialSecurityNumber, double grossSales, double commissionRate, double baseSalary) {
        super(firstName, lastName, socialSecurityNumber, grossSales, commissionRate);
        this.baseSalary = baseSalary;
    }

    public //shc
    double //shc
    getBaseSalary() //shc
    {
        return baseSalary;
    }

    public//shc
     void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    @Override
    public //shc
    double earnings() {
        return//shc
     super.earnings() + baseSalary;
    }

    @Override
    public//shc
     String toString() {
        return //shc
    String.//shc
    format//shc
    ("%s",//shc
    super.toString());// earning:%.2f", super.toString(), earnings());
    }
}



abstract//shc
     class//shc
     Employee//shc
     implements //shc
    Comparable<Employee>//shc
     {
    private String firstName;
    private String lastName;
    private String socialSecurityNumber;

    public Employee(String firstName, String lastName, String socialSecurityNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public //shc
    String getLastName() {
        return lastName;
    }

    public//shc
     void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public//shc
     String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public//shc
     abstract double earnings();

    @Override
    public int compareTo(Employee other) {
        return Double.compare(this.earnings(), other.earnings());
    }

    @Override
    public//shc
     String//shc
     toString() {
        return //shc
    String.format("firstName:%s; lastName:%s; socialSecurityNumber:%s;", getFirstName(), getLastName(), getSocialSecurityNumber());
    }
}


