public class Planet{
	public double xxPos; //Its current x position
	public double yyPos; //Its current y position
	public double xxVel; //Its current velocity in the x direction
	public double yyVel; //Its current velocity in the y direction
	public double mass; //Its mass
	public String imgFileName; //The name of the file that corresponds to the image that depicts the planet

// the first constructor
public Planet(double xP, double yP, double xV,
              double yV, double m, String img){
	xxPos = xP;
	yyPos = yP;
	xxVel = xV;
	yyVel = yV;
	mass = m;
	imgFileName = img;
}

// the second constructor
public Planet(Planet p){
	xxPos = p.xxPos;
	yyPos = p.yyPos;
	xxVel = p.xxVel;
	yyVel = p.yyVel;
	mass = p.mass;
	imgFileName = p.imgFileName;
}

//Method that calculates the distance between two Planets.
public double calcDistance (Planet p) {
    double dx = this.xxPos - p.xxPos;
    double dy = this.yyPos - p.yyPos;
    double r = Math.sqrt(dx * dx + dy * dy);
    return r;
}

//The method takes in a planet, and returns a double 
//describing the force exerted on this planet by the given planet.
public double calcForceExertedBy (Planet p) {
	double G = 6.67e-11;
	double r = calcDistance(p);
	double mSquare = this.mass * p.mass;
	double force = G * mSquare / (r * r) ;
	return force;
}

// The two methods takes in a planet, and returns a double 
//describing exerted in the X and Y directions.
public double calcForceExertedByX (Planet p) {
	double forceT = calcForceExertedBy(p);
    double dx = p.xxPos - this.xxPos;
    double r = calcDistance(p);
    double forceX = forceT * dx / r;
    return forceX;
}
public double calcForceExertedByY (Planet p) {
	double forceT = calcForceExertedBy(p);
    double dy = p.yyPos - this.yyPos;
    double r = calcDistance(p);
    double forceY = forceT * dy / r;
    return forceY;
}

// The two methods each take in an array of Planets 
// and calculate the net X and net Y force exerted by all planets 
// in that array upon the current Planet.
public double calcNetForceExertedByX(Planet[] p) {
    double netFX = 0.0;
    for (int i = 0; i < p.length; i ++) {
        if (p[i] == this) {
            continue;
        } else {
            netFX += calcForceExertedByX(p[i]);
        }
    }
    return netFX;
}

public double calcNetForceExertedByY(Planet[] p) {
    double netFX = 0.0;
    for (int i = 0; i < p.length; i ++) {
        if (p[i] == this) {
            continue;
        } else {
            netFX += calcForceExertedByY(p[i]);
        }
    }
    return netFX;
}
// the method determines how much the forces exerted on the planet 
// will cause that planet to accelerate.
public void update(double dt, double fx, double fy) {
    double aX = fx / this.mass;
    double aY = fy / this.mass;
    xxVel = xxVel + dt * aX;
    yyVel = yyVel + dt * aY;
    xxPos = xxPos + dt * xxVel;
    yyPos = yyPos + dt * yyVel;
}
// draw palnets
public void draw() {
    String imageToDraw = "images/" + imgFileName;      
    StdDraw.picture(this.xxPos, this.yyPos, imageToDraw);
    StdDraw.show();
}




















}








