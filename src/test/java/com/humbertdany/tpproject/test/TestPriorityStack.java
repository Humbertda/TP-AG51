package com.humbertdany.tpproject.test;

import com.humbertdany.tpproject.util.prioritystack.PriorityStack;
import com.humbertdany.tpproject.util.prioritystack.StackNode;


public class TestPriorityStack extends ATest {

	@Override
	public void launch() {
		final PriorityStack q = new PriorityStack();
		q.insert(new StackNode("Test"), 5);
		System.out.println(q.extractMax().getKey());
	}

}
