package com.cctrace.utils; 
  
import java.util.List;  
  
public class Series {  
    public String name;  
    public String type;  
    public List<Double> data;// 这里要用int 不能用String 不然前台显示不正常（特别是在做数学运算的时候）  
      
    public String getName() {  
        return name;  
    }  
  
    public void setName(String name) {  
        this.name = name;  
    }  
  
    public String getType() {  
        return type;  
    }  
  
    public void setType(String type) {  
        this.type = type;  
    }

	/**
	 * @return the data
	 */
	public List<Double> getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(List<Double> data) {
		this.data = data;
	}

	public Series(String name, String type, List<Double> data) {
		super();
		this.name = name;
		this.type = type;
		this.data = data;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Series [name=" + name + ", type=" + type + ", data=" + data
				+ "]";
	}

	

	

	
      
    
} 