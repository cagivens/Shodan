package rosterizer

class Associate(val login: String) {
    companion object {
        const val ROLE_AMBASSADOR = 1
        const val ROLE_EOL = 2
        const val ROLE_PS = 4
        const val ROLE_WATERSPIDER = 8
        const val ROLE_REFURB = 16
        const val ROLE_WHD = 32
        const val ROLE_RECOVERY = 64
        const val ROLE_OUTBOUND = 128
        const val ROLE_SHOES = 256
        const val ROLE_ICQA = 512
        const val ROLE_TDR = 1024
        const val ROLE_PIT = 2048
    }

    private var roles = 0
    private var rate = 0.0
    private var inferredTime = 0.0

    fun addTrainedRole(newRole: Int) { roles = roles or newRole }
    fun hasRole(role: Int): Boolean = (roles and role) == role

    override fun toString(): String {
        return "$login {$roles}"
    }
}