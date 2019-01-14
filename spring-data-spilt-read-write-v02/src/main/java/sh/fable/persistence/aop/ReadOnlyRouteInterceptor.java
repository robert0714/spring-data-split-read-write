package sh.fable.persistence.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sh.fable.persistence.routing.RoutingDataSource;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Dominic Gunn
 */
@Aspect
@Component
@Order(0)
public class ReadOnlyRouteInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(ReadOnlyRouteInterceptor.class);

	/**
	 * 針對是實作的class上有@annotation(transactional)進行AOP，而不是interface上的@annotation(transactional)上作用
	 * */
	@Around("@annotation(transactional)")
	public Object proceedImplentedClass(ProceedingJoinPoint proceedingJoinPoint, Transactional transactional) throws Throwable {
		try {
			if (transactional.readOnly()) {
				RoutingDataSource.setReplicaRoute();
				logger.info("Routing database call to the read replica");
			}
			return proceedingJoinPoint.proceed();
		} finally {
			RoutingDataSource.clearReplicaRoute();
		}
	}
}
