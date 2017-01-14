/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model.register;

import java.util.ArrayList;
import java.util.List;
import lapr.project.model.CDrag;

/**
 *
 * @author zero_
 */
public class CDragRegister {

    private List<CDrag> cDragList;

    public CDragRegister() {
        this.cDragList = new ArrayList<>();
    }

    public CDragRegister(CDragRegister cdragRegister) {
        this();
        for (CDrag cDrag : cdragRegister.getCDragList()){
            this.addCDrag(new CDrag(cDrag));
        }
    }

    public List<CDrag> getCDragList(){
        return this.cDragList;
    }
    
    public boolean addCDrag(CDrag newCDrag) {
        if (validateCDrag(newCDrag)) {
            return this.cDragList.add(newCDrag);
        }

        return false;
    }

    private boolean validateCDrag(CDrag newCDrag) {
        for (CDrag cDrag : this.cDragList) {
            if (newCDrag.equals(cDrag)) {
                return false;
            }
        }

        return true;
    }

    public CDrag getCDrag(int index) {
        return this.cDragList.get(index);
    }

    public int getListSize() {
        return this.cDragList.size();
    }

    @Override
    public String toString() {
        return "CDragRegister{" + "cDragList=" + cDragList + '}';
    }

}
