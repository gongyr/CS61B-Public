public class NBody {
	// Return a double corresponding to the radius of the universe in that file.
	public static double readRadius(String path) {
		In in = new In(path);

		int firstItemInFile = in.readInt();
		double radius = in.readDouble();	
	    return radius;
	}
    // Return an array of Planets corresponding to the planets in the file.
	public static Planet[] readPlanets (String path) {
		In in = new In(path);

        // The first number represent the number of planets
		int n = in.readInt();
		Planet[] planets = new Planet[n];

        // Skip the second number
		in.readDouble();

		// Store the information and create planet object by constructor.
		for (int i = 0; i < n; i++){
		double xP = in.readDouble();
		double yP = in.readDouble();
		double xV = in.readDouble();
		double yV = in.readDouble();
		double m = in.readDouble();
		String img = in.readString();
		planets[i] = new Planet(xP, yP, xV, yV, m, img);
        }

		return planets;
		
	}

	public static void main (String[] args) {

		// Read command line arguments
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);

        // Enable double buffering
		StdDraw.enableDoubleBuffering();

		// Set scale of the univer
    	StdDraw.setScale(-radius, radius);
    	StdDraw.clear();
    	String imageToDraw = "images/starfield.jpg";

    	// Create a time variable
    	double t = 0.0;
    	// Store the number of planets
    	int n = planets.length;

        //Set up a loop to loop until this time variable is T.
    	while (t < T){
    		//Create two double array to store corresponding forces.
    	     double[] xForces = new double[n];
    	     double[] yForces = new double[n];
    	     //Calculate the net x and y forces for each planet.
    	     for(int i = 0; i < n; i++) {
    		    xForces[i] = planets[i].calcNetForceExertedByX(planets);
    		    yForces[i] = planets[i].calcNetForceExertedByY(planets);
    	 }
             //Update each planet’s position, velocity, and acceleration.
    	     for(int i = 0; i < n; i++) {
    	 	    planets[i].update(dt, xForces[i], yForces[i]);
    	 }
             //Draw the background image
    	     StdDraw.picture(0, 0, imageToDraw);
    	     
    	     //Draw all of the planets
    	     for(int i = 0; i < planets.length; i++){
    		     planets[i].draw();
    	}
    	      //Show the offscreen buffer
		      StdDraw.show();
		      //Pause the animation for 10 milliseconds
    	      StdDraw.pause(10);
              //Increase the time variable by dt
    	      t += dt;
         }
        //print out the final state of the universe
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
		    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
		                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		     }

	} 

}











