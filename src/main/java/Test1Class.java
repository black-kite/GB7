import ourAnnotations.AfterSuite;
import ourAnnotations.BeforeSuite;
import ourAnnotations.Test;

public class Test1Class {
    @AfterSuite
    public static void markerAfter(){
        System.out.println("AfterSuite");
    }
    @Test(priority = 7)
    public void testing1(){
        System.out.println("Приоритет 6");
    }
    @Test(priority = 5)    //например сделать 0 или 12
    public void testing2(){
        System.out.println("Приоритет 5");
    }
    @Test(priority = 1)
    public void testing3(){
        System.out.println("Приоритет 1");
    }
    @Test
    public void testing4(){
        System.out.println("Приоритет по умолчанию 5");
    }
    //@Test
    public void testing5(){
        System.out.println("test");
    }
    @BeforeSuite
    public static void markerBefore(){
        System.out.println("BeforeSuite");
    }
}
