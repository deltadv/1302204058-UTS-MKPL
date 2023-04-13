package lib;

public class TaxFunction {

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
    if (isMarried) {
      tax = (int) Math.round(0.05 * (((monthlyIncome.getMonthlySalary() + monthlyIncome.getOtherMonthlyIncome()) * numberOfMonthWorking) - deductible - (54000000 + 4500000 + (numberOfChildren * 1500000))));
    } else {
      tax = (int) Math.round(0.05 * (((monthlyIncome.getMonthlySalary() + monthlyIncome.getOtherMonthlyIncome()) * numberOfMonthWorking) - deductible - 54000000));
    }

    if (tax < 0) {
      return 0;
    } else {
      return tax;
    }

  }
}
