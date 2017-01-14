package com.humbertdany.tpproject.util.graph;

import java.util.ArrayList;
import java.util.List;

abstract public class VertexData {

	protected List<ChangeObserver> changeObservers = new ArrayList<>();

	final public void onChange(ChangeObserver obs){
		this.changeObservers.add(obs);
	}

	final protected void notifyDataUpdate(){
		if(changeObservers.size() != 0){
			this.changeObservers.forEach(ChangeObserver::onChanged);
		}
	}

}
