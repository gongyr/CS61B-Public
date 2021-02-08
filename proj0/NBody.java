public class NBody {
	public static double readRadius(String path) {
		In in = new In(path);

		int firstItemInFile = in.readInt();
		double radius = in.readDouble();	
	    return radius;
	}

	public static Planet[] readPlanets (String path) {
		In in = new In(path);

		int n = in.readInt();
		Planet[] planets = new Planet[n];

		in.readDouble();
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
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);


		StdDraw.enableDoubleBuffering();

    	double t = 0;
    	int n = planets.length;


    	while (t < T){
    	     double[] xForces = new double[n];
    	     double[] yForces = new double[n];
    	     for(int i = 0; i < n; i++) {
    		    xForces[i] = planets[i].calcNetForceExertedByX(planets);
    		    yForces[i] = planets[i].calcNetForceExertedByY(planets);
    	 }

    	     for(int i = 0; i < n; i++) {
    	 	    planets[i].update(dt, xForces[i], yForces[i]);
    	 }

    	StdDraw.setScale(-radius, radius);
		String imageToDraw = "images/starfield.jpg";
		StdDraw.clear();
    	StdDraw.picture(0,0,imageToDraw);

    	
    	      for(int i = 0; i < planets.length; i++){
    		     planets[i].draw();
    	}
		StdDraw.show();
    	StdDraw.pause(10);

    	 t += dt;
         }

		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
		    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
		                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		     }

	} 

}











