package telran.b7a.forum.service.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Slf4j
@Service
public class ForumServiceLogging {
	
	@Pointcut("execution(public java.util.List<telran.b7a.forum.dto.PostDto> telran.b7a.forum.service.ForumServiceImpl.find*(..))")
	public void bulkingFind() {
		
	}
	
	@Pointcut("execution(public telran.b7a.forum.dto.PostDto telran.b7a.forum.service.ForumServiceImpl.findPost(String))" + "&& args(id)")
	public void findById(String id) {
		
	}
	
	@Pointcut("@annotation(PostLogger) && args(.., id)")
	public void annotated(String id) {
		
	}

	
	@Around(value = "bulkingFind()")
	public Object bulkingFindLogging(ProceedingJoinPoint pjp) throws Throwable {
		Object[] args = pjp.getArgs();
		long t1 = System.currentTimeMillis();
		Object res = pjp.proceed();
		long t2 = System.currentTimeMillis();
		log.info("method - {}, duration = {}", pjp.getSignature().toLongString(), (t2 - t1));
		return res;
		
	}
	
	@Before(value = "findById(id)")
	public void findPostById(String id) {
		log.info("post with id {} requested", id);
		
	}
	
	@AfterReturning(value = "annotated(id)")
	public void updatePostLogging(String id) {
		log.info("post with id {} updated, id");
	}
	

}
