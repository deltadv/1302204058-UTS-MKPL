public class Employee {

  private String employeeId;
  private String firstName;
  private String lastName;
  private String idNumber;
  private String address;

  private LocalDate dateJoined;
  private int monthWorkingInYear;

  private Nationality nationality;
  private Gender gender;

  private int monthlySalary;
  private int otherMonthlyIncome;
  private int annualDeductible;

  private String spouseName;
  private String spouseIdNumber;

  private List < Child > children;
  
  private static final int GRADE_1_SALARY = 3000000;
  private static final int GRADE_2_SALARY = 5000000;
  private static final int GRADE_3_SALARY = 7000000;
  private static final double FOREIGN_EMPLOYEE_SALARY_INCREASE = 1.5;

  public Employee(String employeeId, String firstName, String lastName, String idNumber, String address, LocalDate dateJoined, Nationality nationality, Gender gender) {
    this.employeeId = employeeId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.idNumber = idNumber;
    this.address = address;
    this.dateJoined = dateJoined;
    this.nationality = nationality;
    this.gender = gender;

    children = new LinkedList < Child > ();
  }

  /**
   * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade kepegawaiannya (grade 1: 3.000.000 per bulan, grade 2: 5.000.000 per bulan, grade 3: 7.000.000 per bulan)
   * Jika pegawai adalah warga negara asing gaji bulanan diperbesar sebanyak 50%
   */

  public void setMonthlySalary(int grade) {
    if (grade == 1) {
      monthlySalary = GRADE_1_SALARY;
      if (nationality == Nationality.FOREIGNER) {
        monthlySalary = (int)(GRADE_1_SALARY * FOREIGN_EMPLOYEE_SALARY_INCREASE);
      }
    } else if (grade == 2) {
      monthlySalary = GRADE_2_SALARY;
      if (nationality == Nationality.FOREIGNER) {
        monthlySalary = (int)(GRADE_2_SALARY * FOREIGN_EMPLOYEE_SALARY_INCREASE);
      }
    } else if (grade == 3) {
      monthlySalary = GRADE_3_SALARY;
      if (nationality == Nationality.FOREIGNER) {
        monthlySalary = (int)(GRADE_3_SALARY * FOREIGN_EMPLOYEE_SALARY_INCREASE);
      }
    }
  }

  public void setAnnualDeductible(int deductible) {
    this.annualDeductible = deductible;
  }

  public void setAdditionalIncome(int income) {
    this.otherMonthlyIncome = income;
  }

  public void setSpouse(String spouseName, String spouseIdNumber) {
    this.spouseName = spouseName;
    this.spouseIdNumber = idNumber;
  }

  public void addChild(String childName, String childIdNumber) {
    Child child = new Child(childName, childIdNumber);
    children.add(child);
  }

  public int getAnnualIncomeTax() {
    int taxableIncome = calculateTaxableIncome();
    int dependantDeduction = calculateDependantDeduction();
    int annualIncomeTax = TaxFunction.calculateTax(taxableIncome, dependantDeduction);
    return annualIncomeTax;
  }

  private int calculateTaxableIncome() {
    int monthlyIncome = monthlySalary + otherMonthlyIncome;
    int annualIncome = monthlyIncome * 12;
    int deductibleIncome = annualDeductible * 12;
    int taxableIncome = annualIncome - deductibleIncome;
    return taxableIncome;
  }

  private int calculateDependantDeduction() {
    int dependantDeduction = 0;
    if (spouseIdNumber.equals("")) {
      dependantDeduction += 4500000;
    }
    dependantDeduction += children.size() * 2000000;
    return dependantDeduction;
  }

  public class Child {
    private String childName;
    private String childIdNumber;

    public Child(String childName, String childIdNumber) {
      this.childName = childName;
      this.childIdNumber = childIdNumber;
    }

    public String getChildName() {
      return childName;
    }

    public void setChildName(String childName) {
      this.childName = childName;
    }

    public String getChildIdNumber() {
      return childIdNumber;
    }

    public void setChildIdNumber(String childIdNumber) {
      this.childIdNumber = childIdNumber;
    }
  }
}
