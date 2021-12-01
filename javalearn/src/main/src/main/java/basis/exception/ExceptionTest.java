package basis.exception;

/**
 * 异常抛出的顺序
 * @author LL
 */
public class ExceptionTest {

    public static void main(String args[]) {
        //整个流程就是正常的try catch走，而且2个finally都会走
        try {
            throw new ArrayIndexOutOfBoundsException();
        } catch (Exception e) {
            try {
                throw new ArrayStoreException ();
            } catch (Exception e1) {
                int i = 1/0;
                System.out.println("==>抛出异常2" + e1.getMessage());
            }
            finally {
                int i = 1/0;
                System.out.println("==>抛出异常3");
            }
            System.out.println("==>抛出异常1" + e.getMessage());
        }finally {
            int i = 2/0;
            System.out.println("==>抛出异常4");
        }

    }

}
