/**
 *
 * @author xbp1
 */
import org.junit.*;

public class HeapQueueTest {

    @Test
    public void insertionTest() {
        HeapQueue<Integer, String> testQueue = new HeapQueue<>();
        
        //Inicialitzem unes quantes parelles a la testQueue
        testQueue.insert(7, "test1");
        testQueue.insert(3, "test2");
        testQueue.insert(2, "test3");
        testQueue.insert(10, "test4");
        testQueue.insert(5, "test5");
        
        //Un cop insertades 5 parelles, comprovem que la mida és 5
        Assert.assertEquals(5, testQueue.size());
    }
    
    @Test
    public void extractTest() {
        HeapQueue<Integer, String> testQueue = new HeapQueue<>();
        
        //Inicialitzem unes quantes parelles a la testQueue
        testQueue.insert(7, "test1");
        testQueue.insert(3, "test2");
        testQueue.insert(2, "test3");
        testQueue.insert(10, "test4");
        testQueue.insert(5, "test5");
        
        //Comprovem que l'extract ens torna la value de la parella amb prioritat més alta
        String test = testQueue.extract();
        Assert.assertEquals("test4", test);
        //Comprovem que s'ha reduït la mida de l'ArrayList eliminant l'element retornat
        Assert.assertEquals(4, testQueue.size());
        
        //Seguim fent els tests segons les prioritats i comprovant el nombre d'elements
        test = testQueue.extract();
        Assert.assertEquals("test1", test);
        Assert.assertEquals(3, testQueue.size());
        
        test = testQueue.extract();
        Assert.assertEquals("test5", test);
        Assert.assertEquals(2, testQueue.size());
        
        test = testQueue.extract();
        Assert.assertEquals("test2", test);
        Assert.assertEquals(1, testQueue.size());
        
        test = testQueue.extract();
        Assert.assertEquals("test3", test);
        Assert.assertEquals(0, testQueue.size());
    }
}
