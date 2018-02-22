package es.nimio.exercise.client.commands.customers

import com.github.rvesse.airline.annotations.Command
import com.github.rvesse.airline.annotations.Option
import es.nimio.exercise.client.commands.BaseCommandWithId
import es.nimio.exercise.client.service.bodyOf
import es.nimio.exercise.client.service.postEntity
import es.nimio.exercise.client.service.withData
import es.nimio.exercise.client.service.withUrl
import es.nimio.exercise.model.api.ImageData
import es.nimio.exercise.util.encodeFromPath
import org.apache.commons.lang3.StringUtils
import java.io.File

@Command(name = "set", description = "Sets the image of a customer")
class SetCustomerImageCommand : BaseCommandWithId() {

    @Option(name = ["--file", "-f"], description = "File with the image to upload")
    var imagePath: String? = null

    override fun validate(): Boolean {
        var result = super.validate()
        if (StringUtils.isEmpty(imagePath)) {
            println("You have to define the path where load the image file")
            result = false
        }
        return result
    }

    private fun setCustomerImage(): ImageData =
            File(imagePath!!).let {fileImage ->
                bodyOf(restClient withUrl
                        "$host/customers/${customerId!!}/image" withData
                        ImageData(fileImage.name, imagePath!!.encodeFromPath()) postEntity
                        ImageData::class.java)
            }

    override fun doRun() {
        println("Associate the image '$imagePath' to customer with id: ${customerId!!}")
        setCustomerImage()
    }
}