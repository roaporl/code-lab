//import com.github.zkclient.IZkDataListener;
//import com.github.zkclient.ZkClient;
//
//import java.util.Date;
//
//public class ZKDemo {
//
//  public static void main(String[] args) {
//
//    ZkClient zkClient = new ZkClient("10.237.114.98:2181", 3000);
////    zkClient.readData("/test/file");
//        zkClient.subscribeDataChanges("/test/file", new IZkDataListener() {
//          @Override
//          public void handleDataChange(String dataPath, byte[] data) throws Exception {
//            System.out.println("changed to : " + new String(data, 0, data.length, "UTF-8"));
//          }
//
//          @Override
//          public void handleDataDeleted(String dataPath) throws Exception {
//            System.out.println("deleted");
//          }
//        });
//    while (true) {
//      try {
////        zkClient.readData("/test/file");
////        System.out.println(new Date() + "\tread data from /test/file");
////        System.out.println(new Date() + "\tSLEEP");
//        Thread.sleep(5000);
//      } catch (InterruptedException e) {
//        e.printStackTrace();
//      }
//    }
//  }
//
//}
