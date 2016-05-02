package com.goforit.common.service.lock.server;

import com.goforit.common.service.lock.LockBaseTest;
import com.goforit.common.service.lock.model.dcs.LockRequest;
import com.goforit.common.service.lock.model.dcs.LockResult;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by junqingfjq on 16/5/1.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:core-service.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ServicePerformanceTest extends LockBaseTest {


    @Test
    @DirtiesContext
//    @Transactional
    public void testBatchLock(){

        int loopNum=10;

        Date oldTime=new Date();

        List<LockThread> threads=new ArrayList<LockThread>();

        for(int i=0;i<loopNum;i++){
            LockThread lockThread=new LockThread();
            lockThread.start();
            threads.add(lockThread);
        }

        
        for (int i = 0; i < loopNum; i++) {
            try {
                threads.get(i).join();

            } catch (Exception e) {
                System.err.println(e);
            }
        }

        System.err.println("caculate time: "+caculateTime(oldTime,new Date()));


    }


    class LockThread extends Thread{

        @Override
        public void run(){

            for(int i=0;i<1;i++){

                LockRequest request=LockRequest.buildRequest(RESOURCE_NAME,RESOURCE_TYPE,LOCK_NAME,OWNER);

                System.err.println("begin to lock resource");
                LockResult result=dcsLockService.lockResource(request);
                System.err.println("end lock resource");

                Assert.assertTrue(null != result);

                if(result.isLockedByMe()){
                    Assert.assertTrue(null!=result.getOwnedDcsResourceLock());

                    baseResourceId=result.getOwnedDcsResourceLock().getResourceId();
                    baseLockId=result.getOwnedDcsResourceLock().getId();
                    String uniqueBizId=result.getOwnedDcsResourceLock().getUniqueBizId();

                    Assert.assertTrue(StringUtils.isNotBlank(uniqueBizId));
                    Assert.assertTrue(StringUtils.isNotBlank(baseResourceId));
                    Assert.assertTrue(StringUtils.isNotBlank(baseLockId));

//                    try{
//                        Thread.sleep(1000);
//                    }catch (Exception e){
//
//                    }
                    System.err.println("begin to release lock");
                    dcsLockService.releaseLockByBizId(uniqueBizId);
                    System.err.println("end release lock");
                }else {
                    System.err.println("lock resource failed");
                    Assert.assertTrue(null!=result.getOthersDcsResourceLock());
                }

            }

        }
    }
}
