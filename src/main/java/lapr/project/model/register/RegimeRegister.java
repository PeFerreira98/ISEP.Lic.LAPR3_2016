/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model.register;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import lapr.project.model.Regime;

/**
 *
 * @author zero_
 */
public class RegimeRegister {

    private Map<String, Regime> regimeMap;

    public RegimeRegister() {
        this.regimeMap = new LinkedHashMap<>();
    }

    public Regime addRegime(Regime newRegime) {
        if (validateRegime(newRegime)) {
            this.regimeMap.put(newRegime.getId(), newRegime);
            return this.regimeMap.get(newRegime.getId());
        }

        return null;
    }

    private boolean validateRegime(Regime newRegime) {
        for (Regime regime : this.regimeMap.values()) {
            if (newRegime.equals(regime)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final RegimeRegister other = (RegimeRegister) obj;
        if (!Objects.equals(this.regimeMap.size(), other.regimeMap.size())) {
            return false;
        }
        
        return Objects.equals(this.regimeMap, other.regimeMap);
    }

    @Override
    public String toString() {
        return "RegimeRegister{" + "regimeMap=" + regimeMap + '}';
    }
    
}
