package lang.immutable.test;

public class MyDateImmutable {

    private final int year;
    private final int month;
    private final int day;

    @Override
    public String toString() {
        return "MyDate{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                '}';
    }

    public MyDateImmutable withYear(int newYear) {
        return new MyDateImmutable(newYear, month, day);
    }

    public MyDateImmutable setMonth(int newMonth) {
        return new MyDateImmutable(year, newMonth, day);
    }

    public MyDateImmutable setDay(int newDay) {
        return new MyDateImmutable(year, month, newDay);
    }

    public MyDateImmutable(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }


    public int getYear() {
        return year;
    }


    public int getMonth() {
        return month;
    }


    public int getDay() {
        return day;
    }

}
