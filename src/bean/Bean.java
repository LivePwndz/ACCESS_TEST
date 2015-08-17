package bean;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;
import java.io.Serializable;

public class Bean implements Serializable {
	/*
	 * We can enhance our ordinary properties of the bean to have other interesting features for example
	 * 1. Fire and event whenever the value changes.
	 * (1.e. Make use of interface PropertyChangeListener, PropertyChangeEvent.).
	 * The ProperyChangeSupport class is a Convenient class that bundles all the set listeners of the Properties.
	 * 
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private int color;
	private int size;
	
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private VetoableChangeSupport vcs = new VetoableChangeSupport(this);
	
	public Bean(){
		this(1,2);
	}
	
	private Bean(int c, int s){
		color = c; size = s;
	}
	

	public static void main(String[] args){
		/*
		 * Instantiation of our bean.
		 * Note: Our bean has two constructors, on is nullary, the other takes two arguments of both
		 * color and size.
		 * 
		 */
		Bean b = new Bean();
		
		
		
		b.addPropertyChangeListener(new PropertyChangeListener(){

			public void propertyChange(PropertyChangeEvent e) {
				System.out.println("Changing from Color: "+e.getOldValue().toString()+" to: "+e.getNewValue());
				
			}
			
		});
		
		b.addVetoableChangeListener(new VetoableChangeListener(){

			public void vetoableChange(PropertyChangeEvent e)
					throws PropertyVetoException {
				if(e.getNewValue().equals(e.getOldValue()))
					throw new PropertyVetoException("New value: "+e.getNewValue()+"is same as Old value: "+e.getOldValue()+"   Setting aborted!", e);
				
			}
			
		});
		
		System.out.println("Default Color: "+b.getColor()+" Default Size: "+b.getSize());
		
			try {
				b.setColor(2);
			} catch (PropertyVetoException e1) {
				System.out.println("Exception occured.");
				e1.printStackTrace();
			}
		
		b.setSize(9);
		System.out.println("Custom Color: "+b.getColor()+" Custom Size: "+b.getSize());
	}
	
	/*
	 * set bean size
	 */
	
	public void setColor(int c) throws PropertyVetoException{
		pcs.firePropertyChange("Color changing", color, c);
		vcs.fireVetoableChange("Changeable", color, c);
		color = c;
	}
	
	/*
	 * get bean color
	 */
	public int getColor(){
		
		return color;
	}
	
	/*
	 * set bean color
	 * 
	 */
	
	public void setSize(int s){
		
		size = s;
	}
	
	
	
	/*
	 * get bean size
	 */
	public int getSize(){
		
		return size;
	}
	
	
	public void addPropertyChangeListener(PropertyChangeListener pcl){
		pcs.addPropertyChangeListener(pcl);
	
	}
	
	
	public void removePropertyChangeListener(PropertyChangeListener pcl){
		pcs.removePropertyChangeListener(pcl);
	}
	
	public void addVetoableChangeListener(VetoableChangeListener vcl){
		vcs.addVetoableChangeListener(vcl);
		
	}
	
	public void removeVetoableChangeListener(VetoableChangeListener vcl){
		vcs.removeVetoableChangeListener(vcl);
	}
	
	
	

}
