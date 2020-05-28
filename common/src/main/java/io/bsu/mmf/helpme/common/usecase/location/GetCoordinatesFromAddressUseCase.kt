package io.bsu.mmf.helpme.common.usecase.location

import io.bsu.mmf.helpme.common.repository.geocoding.GeocodeRepository
import io.bsu.mmf.helpme.data.ResultNetwork
import io.bsu.mmf.helpme.data.location.AddressCoordinate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetCoordinatesFromAddressUseCase(
    private val geocodeRepository: GeocodeRepository
) {

    suspend operator fun invoke(address: String): ResultNetwork<AddressCoordinate> {
        return withContext(Dispatchers.IO) {
            if (address.isNotEmpty()) {
                geocodeRepository.getLocationFromAddress(address)
            } else {
                ResultNetwork.Error("Enter address")
                //AddressCoordinate(0.0,0.0)
            }
        }
    }

}