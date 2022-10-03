package home.work;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public   class DI {



    public static ApplicationContext getAppContext()
    {
        if (appContext==null)
            appContext= new AnnotationConfigApplicationContext(MyApplicationContextConfiguration.class);
        return appContext;
    }
  private    static ApplicationContext appContext;

}
