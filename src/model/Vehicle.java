package model;

import java.awt.*;

public abstract class Vehicle extends Observable implements Movable {

    protected int nrDoors; 				
    protected double enginePower; 		
    protected double currentSpeed = 0;
    protected Color color;			
    protected String modelName;	
	protected double[] position = new double[2];
	protected Direction direction = Direction.NORTH;

	public enum Direction {
		NORTH, EAST, SOUTH, WEST;

		private static final Direction[] directions = values();

		public Direction left() {
			return directions[Math.floorMod(this.ordinal() - 1, 4)];
		}

		public Direction right() {
			return directions[Math.floorMod(this.ordinal() + 1, 4)];
		}
	}

	public Vehicle(int nrDoors, double enginePower, Color color, String modelName) {
        this.nrDoors = nrDoors;
		this.enginePower = enginePower;
		this.color = color;
		this.modelName = modelName;
	}

	public void move() {
		switch(direction) {
			case NORTH: position[1] += currentSpeed; break;
			case EAST: 	position[0] += currentSpeed; break;
			case SOUTH: position[1] -= currentSpeed; break;
			case WEST: 	position[0] -= currentSpeed; break;
		}
		change = true;
		notifyObservers();
	}

	public void turnLeft() {
		direction = direction.left();
	}

	public void turnRight() {
		direction = direction.right();
	}

    public int getNrDoors() {
        return nrDoors;
    }
    public double getEnginePower() {
        return enginePower;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color clr) {
	    color = clr;
    }

	public String getModelName() {
		return modelName;
	}

	public double[] getPosition() {
		return position;
	}

	public void setPosition(double[] amount) {
		position = amount;
	}

	public Direction getDirection() {
		return direction;
	}

    public void startEngine() {
	    currentSpeed = 0.1;
    }

    public void stopEngine() {
	    currentSpeed = 0;
    }

    public double speedFactor() {
        return enginePower * 0.01;
    }

    private void incrementSpeed(double amount) {
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower);
    }

	private void decrementSpeed(double amount) {
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
    }
    
    public void gas(double amount) {
		if (amount > 1 || amount < 0) return;
		incrementSpeed(amount);
    }

    public void brake(double amount) {	
		if (amount > 1 || amount < 0) return;
        decrementSpeed(amount);
    }
}
