package wat.app.taskmanager.prefs

import bakuen.lib.protostore.ProtoStore
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.protobuf.ProtoNumber

@Serializable
data class Settings @OptIn(ExperimentalSerializationApi::class) constructor(
    @ProtoNumber(1)
    val hidePackages: Set<String> = setOf()
) : ProtoStore