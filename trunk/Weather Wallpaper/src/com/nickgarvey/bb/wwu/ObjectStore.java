package com.nickgarvey.bb.wwu;

import net.rim.device.api.system.PersistentObject;
import net.rim.device.api.system.PersistentStore;

public abstract class ObjectStore {
	
	protected Object objToStore;
	
	private PersistentObject persist;
	
	protected ObjectStore(long key) {

		persist = PersistentStore.getPersistentObject(key);
		
		Object contents = persist.getContents();

		if (contents == null) {
			objToStore = getInitialObj();
			save();
		} else {
			objToStore = contents;
		}

	}
	
	protected abstract Object getInitialObj();
	
	/**
	 * Saves the current mapping to the device. This should be called after
	 * every modification to the mapping.
	 */
	protected void save() {
		
		persist.setContents(objToStore);
		persist.commit();
		
	}

}
