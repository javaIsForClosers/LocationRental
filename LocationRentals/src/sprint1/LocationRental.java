package sprint1;

import java.text.NumberFormat;

public class LocationRental {

	String locationName;
	int locationId;
	int zipCode;
	double rentalRate;
	int totalNumberCars;
	int rentedCars;

	public LocationRental(String locationName, int locationId, int zipCode, double rentalRate, int totalNumberCars,
			int rentedCars) {
		super();
		this.locationName = locationName;
		this.locationId = locationId;
		this.zipCode = zipCode;
		this.rentalRate = rentalRate;
		this.totalNumberCars = totalNumberCars;
		this.rentedCars = rentedCars;
	}

	// method to calculate daily revenue
	public double calculateDailyRevenue() {
		return rentalRate * rentedCars;
	}

	@Override
	public String toString() {
		return String.format(
				" Location name: %s \r Location ID: %s \r Location Zipcode: %s \r Daily Rental Rate: %s \r Total Cars: %s \r Cars in Use: %s",
				locationName, locationId, zipCode, NumberFormat.getCurrencyInstance().format(rentalRate),
				totalNumberCars, rentedCars);
	}
}
