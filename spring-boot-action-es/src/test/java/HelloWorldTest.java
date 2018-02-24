import com.spring.boot.action.es.Application;
import com.spring.boot.action.es.model.Article;
import com.spring.boot.action.es.respository.ArticleSearchRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * Created by fgm on 2017/9/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
public class HelloWorldTest {


    @Autowired
    private ArticleSearchRepository articleSearchRepository;


    @Test
    public void test(){
        System.out.println("演示初始化");
    }

    @Test
    public void testSave(){
        Article article=new Article();
        article.setId(2L);
        article.setTitle("elasticsearch教程");
        article.setSunnyDate("2018-10-01");
        article.setClickCount(100l);
        article.setContent("SpringBoot与spring-data-elastichSearch整合");
        article.setAbstracts("spring-data-elastichSearch");
        article.setHello("hello elasticsearch!");
        article.setDate("2017-10-02");
        article.setPublishAuthor("方光明");
        Article article1=  articleSearchRepository.save(article);
        System.out.println(article1.toString());
    }

    @Test
    public void testGet(){
        Article article= articleSearchRepository.findOne(2L);
        System.out.println(article);

    }


}
