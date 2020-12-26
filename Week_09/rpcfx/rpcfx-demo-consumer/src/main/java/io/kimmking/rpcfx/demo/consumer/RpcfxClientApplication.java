package io.kimmking.rpcfx.demo.consumer;

import io.kimmking.rpcfx.filter.Filter;
import io.kimmking.rpcfx.cluster.LoadBalancer;
import io.kimmking.rpcfx.cluster.Router;
import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.client.Rpcfx;
import io.kimmking.rpcfx.demo.api.Order;
import io.kimmking.rpcfx.demo.api.OrderService;
import io.kimmking.rpcfx.demo.api.User;
import io.kimmking.rpcfx.demo.api.UserService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Random;

public class RpcfxClientApplication {
	public static void main(String[] args) {

		UserService userService = Rpcfx.create(UserService.class, "http://localhost:8081/");
		User user = userService.findById(1);
		System.out.println("find user id=1 from server: " + user.getName());

		OrderService orderService = Rpcfx.create(OrderService.class, "http://localhost:8081/");
		Order order = orderService.findOrderById(1992129);
		System.out.println(String.format("find order name=%s, amount=%f",order.getName(),order.getAmount()));


		UserService userService2 = Rpcfx.createFromRegistry(UserService.class, "localhost:2181", new TagRouter(), new RandomLoadBalancer(), new CuicuiFilter());
		User user2 = userService2.findById(1992129);

		System.out.println(String.format("find user=%s from server: ", user2));
	}

	private static class TagRouter implements Router {
		@Override
		public List<String> route(List<String> urls) {
			return urls;
		}
	}

	private static class RandomLoadBalancer implements LoadBalancer {
		@Override
		public String select(List<String> urls) {
			return urls.get(0);
		}
	}

	@Slf4j
	private static class CuicuiFilter implements Filter {
		@Override
		public boolean filter(RpcfxRequest request) {
			log.info("filter {} -> {}", this.getClass().getName(), request.toString());
			return true;
		}
	}
}



