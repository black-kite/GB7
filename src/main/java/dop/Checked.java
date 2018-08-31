package dop;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Iterator;

public class Checked {
    public static void main(String[] args) throws Exception {
        Checked cheackHW = new Checked();
        cheackHW.testSum();
    }

    public void testSum() throws Exception {
        File file = new File("testtt");
        String[] str = file.list();

        ArrayList<String> fileName = new ArrayList<>();

        for (String o: str) {
            String[] mass = o.split("\\.");
            if(mass[1].equalsIgnoreCase("class")) {
                fileName.add(mass[0]);
            }
        }

        Iterator it = fileName.iterator();
        while (it.hasNext()) {
            String name = String.valueOf(it.next());
            Class ch = URLClassLoader.newInstance(new URL[]{new File("testtt").toURL()})
                    .loadClass(name);
            Constructor constructor = ch.getConstructor();
           Object calc = constructor.newInstance();

            Method m = ch.getDeclaredMethod("Calculat", int.class, int.class, int.class, int.class);

            int res = (Integer) m.invoke(calc, 2,2,2,2);
            System.out.println(res);
            if(res == 6) {
                System.out.println(name + " Выполнил ДЗ");
            } else  {
                System.out.println(name + " не выполнил ДЗ");
            }

            Object bool = constructor.newInstance();
            Method mBool = ch.getDeclaredMethod("checkNumber",int.class);
            boolean b = (boolean) mBool.invoke(bool,8);
            if (b == false) {
                System.out.println("Верно, по условию!");
            }else System.out.println("Не правильно!!!!!!!!!!");

            Method mBool2 = ch.getDeclaredMethod("ChekSum",int.class,int.class);
            boolean b2 = (boolean) mBool2.invoke(bool,12,7);
            if (b2 == true) {
                System.out.println("Правильно! число входит в диапозон");
            }else System.out.println("Не правильно!!!!!!!!!!");

//            Object currentNum = constructor.newInstance();
//            Method num = ch.getDeclaredMethod("isPositiveOrNegative",int.class);
//            Object nnnn = num.invoke(currentNum,4);
//            if(nnnn.toString().equals("Число Положительное")) {
//                System.out.println("Зачет");
//            }else System.out.println("Не зачет");
        }

    }

}