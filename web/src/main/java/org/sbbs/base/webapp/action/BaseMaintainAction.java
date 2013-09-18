package org.sbbs.base.webapp.action;

import java.io.Serializable;

public class BaseMaintainAction<T, ID extends Serializable>
    extends BaseDwzAction {

    protected ID id;

    private String ids;

    private int editType;

    protected static final int EDITTYPE_EDIT = 0;

    protected static final int EDITTYPE_ADD = 1;


    public ID getId() {
        return id;
    }

    public void setId( ID id ) {
        this.id = id;
    }

    public String getIds() {
        return ids;
    }

    public void setIds( String ids ) {
        this.ids = ids;
    }

    public int getEditType() {
        return editType;
    }

    public void setEditType( int editType ) {
        this.editType = editType;
    }

    public void setEditTypeEdit() {
        this.editType = EDITTYPE_EDIT;
    }

    public void setEditTypeAdd() {
        this.editType = EDITTYPE_ADD;
    }

    public boolean isNew() {
        return this.getEditType() == EDITTYPE_ADD;
    }
    
    private String gridId;


    public String getGridId() {
        return gridId;
    }

    public void setGridId( String gridId ) {
        this.gridId = gridId;
    }
    
}
