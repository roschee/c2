package ch.rmuerner.c2.db.dao;

import org.h2.upgrade.DbUpgrade;

import ch.rmuerner.c2.db.DBAdapter;
import ch.rmuerner.c2.db.dto.AbstractDTO;

public abstract class AbstractDAO<T extends AbstractDTO>{

	private DBAdapter dbAdapter;
	private String idColumnName;
	public abstract String getTableName();
	
	public AbstractDAO(DBAdapter dbAdapter, String idColumnName){
		this.dbAdapter = dbAdapter;
        this.idColumnName = idColumnName;
	}
	
	public void save(T dto){
        if (dto.getId() == -1)
        {
//        	dbAdapter.s
//            ContentValues values = dto.getContentValues();
//            values.remove(idColName);
//            dto.setId(dbUtility.insert(getTableName(), null, values));
        	dbAdapter.save(getTableName());
        }
        else
        {
//            String[] args = {Long.toString(dto.getId())};
//            dbUtility.update(getTableName(), dto.getContentValues(), idColName + "=?", args);
        }
	}
	
	public void delete(T dto){
		
	}
}
