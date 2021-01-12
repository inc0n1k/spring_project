package n1k.spring_project.service;

import n1k.spring_project.model.OrderProduct;
import n1k.spring_project.repository.OrderProductRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderProductService {

	private final OrderProductRepository orderProductRepository;

	public OrderProductService(OrderProductRepository orderProductRepository) {
		this.orderProductRepository = orderProductRepository;
	}

	public void saveOrderProduct(OrderProduct orderProduct) {
		orderProductRepository.save(orderProduct);
	}

	public void saveOrderProductNow(OrderProduct orderProduct) {
		orderProductRepository.saveAndFlush(orderProduct);
	}
}
