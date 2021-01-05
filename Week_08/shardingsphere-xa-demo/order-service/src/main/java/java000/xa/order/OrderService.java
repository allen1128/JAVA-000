package java000.xa.order;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.net.www.http.HttpClient;

@Service
public class OrderService {

    @Autowired private OrderDao orderDao;

    public Order findById(int id) {
        return orderDao.findById(id);
    }


    @Transactional(rollbackFor = RuntimeException.class)
    @ShardingTransactionType(TransactionType.XA)
    public void add(Order order) {
        orderDao.add(order);

        String url = String.format("http://localhost:8082/storage/update?commodityId=" + order.getCommodity_id() +
                "&quantity=" + order.getQuantity());
        HttpPost httpGet = new HttpPost(url);
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            System.out.println(EntityUtils.toString(httpResponse.getEntity()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
