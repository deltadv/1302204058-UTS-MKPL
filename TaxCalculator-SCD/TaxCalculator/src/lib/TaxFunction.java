package lib;

public class TaxFunction {

  private static final int TAX_EXEMPTION_SINGLE = 54000000;
  private static final int TAX_EXEMPTION_MARRIED = 58500000;
  private static final int TAX_EXEMPTION_CHILD = 1500000;
  private static final double TAX_RATE = 0.05;

  public static class MonthlyIncome {
    private int monthlySalary;
    private int otherMonthlyIncome;

    public MonthlyIncome(int monthlySalary, int otherMonthlyIncome) {
      this.monthlySalary = monthlySalary;
      this.otherMonthlyIncome = otherMonthlyIncome;
    }

    public int getMonthlySalary() {
      return monthlySalary;
    }

    public int getOtherMonthlyIncome() {
      return otherMonthlyIncome;
    }
  }

  public static class EmployeeFamily {
    private boolean isMarried;
    private int numberOfChildren;

    public EmployeeFamily(boolean isMarried, int numberOfChildren) {
      this.isMarried = isMarried;
      this.numberOfChildren = numberOfChildren;
    }

    public boolean getIsMarried() {
      return isMarried;
    }

    public int getNumberOfChildren() {
      return numberOfChildren;
    }
  }

  /**
   * Menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
   * 
   * @param monthlyIncome Penghasilan bulanan pegawai (gaji dan pemasukan bulanan lainnya).
   * @param numberOfMonthWorking Jumlah bulan yang dikerjakan pegawai dalam setahun.
   * @param deductible Pengurang dari penghasilan bruto.
   * @param employeeFamily Keluarga pegawai (status perkawinan dan jumlah anak).
   * @return Jumlah pajak penghasilan yang harus dibayarkan setahun.
   */
  public static int calculateTax(MonthlyIncome monthlyIncome, int numberOfMonthWorking, int deductible, EmployeeFamily employeeFamily) {

    int tax = 0;

    if (numberOfMonthWorking > 12) {
      System.err.println("More than 12 month working per year");
    }

    int numberOfChildren = employeeFamily.getNumberOfChildren();
    if (numberOfChildren > 3) {
      numberOfChildren = 3;
    }

    boolean isMarried = employeeFamily.getIsMarried();
    int taxExemption = isMarried ? TAX_EXEMPTION_MARRIED : TAX_EXEMPTION_SINGLE;
    if (numberOfChildren > 0) {
      taxExemption += numberOfChildren * TAX_EXEMPTION_CHILD;
    }

    tax = (int) Math.round(TAX_RATE * (((monthlyIncome.getMonthlySalary() + monthlyIncome.getOtherMonthlyIncome()) * numberOfMonthWorking) - deductible - taxExemption));

    if (tax < 0) {
      return 0;
    } else {
      return tax;
    }

  }
}
