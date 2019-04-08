package Aces;
import Extreme21.Extreme21Game;

public class Ace {
	
	protected String name;
	protected String description;
	protected Boolean isActive=false;

	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Boolean getIsActive() {
		return isActive;
	}
	
	public void use(Extreme21Game game){
	}
	
	public void removeFromPlay(Extreme21Game game){
	}
	
	public String toString(){
		return this.name;
	}
}
