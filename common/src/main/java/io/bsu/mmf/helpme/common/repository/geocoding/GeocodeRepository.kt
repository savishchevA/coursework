package io.bsu.mmf.helpme.common.repository.geocoding

import android.location.Geocoder
import io.bsu.mmf.helpme.data.location.AddressCoordinate

interface GeocodeRepository {
   suspend fun getLocationFromAddress(address: String): AddressCoordinate
}

class GeocodeRepositoryImpl(
    private val geocoder: Geocoder
) : GeocodeRepository {

    override suspend fun getLocationFromAddress(address: String): AddressCoordinate {
        val foundAddress = geocoder.getFromLocationName(address, 1)
        return if (foundAddress.isNotEmpty()) {
            AddressCoordinate(
                latitude = foundAddress[0].latitude,
                longitude = foundAddress[0].longitude
            )
        } else {
            AddressCoordinate(0.0, 0.0)
        }
    }
}