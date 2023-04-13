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
   * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
   * 
   * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan) dikurangi penghasilan tidak kena pajak.
   * 
   * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena pajaknya adalah Rp 54.000.000.
   * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000.
   * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
   * 
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
