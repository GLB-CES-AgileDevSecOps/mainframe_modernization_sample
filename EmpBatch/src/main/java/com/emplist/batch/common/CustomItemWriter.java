package com.emplist.batch.common;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.support.transaction.TransactionAwareProxyFactory;

import com.emplist.batch.copybooks.TestBatchInfilerecord.TestBatchoutrecord;

public class CustomItemWriter implements ItemWriter<TestBatchoutrecord> {

	//private static final Logger log = LogManager.getLogger(CustomItemWriter.class);
	
	List<TestBatchoutrecord> output = TransactionAwareProxyFactory.createTransactionalList();
	
	@Override
	public void write(List<? extends TestBatchoutrecord> items) throws Exception {
		//log.info("Entering into write()  --  TestpgmWsoutrecord :: "+items);
		output.addAll(items);
		
		//log.info("Exiting from write()  --  TestpgmWsoutrecord :: ");
	}
	
	public List<TestBatchoutrecord> getOutput() {
        return output;
    }
	
}