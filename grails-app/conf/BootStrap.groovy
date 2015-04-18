import com.knowledgenet.knkpi.Role
import com.knowledgenet.knkpi.Setting
import com.knowledgenet.knkpi.User
import com.knowledgenet.knkpi.UserRole

class BootStrap {

    def init = { servletContext ->
        Setting setting = Setting.findOrSaveWhere(auth: 'NLAuth nlauth_account=3388776, nlauth_email=jimmyfrost@gmail.com, nlauth_signature=AbeFroman1, nlauth_role=3',
            baseUrl: 'https://rest.netsuite.com/app/site/hosting/',
            searchesUrl: 'restlet.nl?script=108&deploy=1',
            employeesUrl: 'restlet.nl?script=109&deploy=1').save()

        def userRole = Role.findOrSaveWhere(authority: Role.USER)
        def adminRole = Role.findOrSaveWhere(authority: Role.ADMIN)

        def nick = User.findByUsername('nick') ?: new User(username: 'nick', password: 'NickKpi123').save()
        def jimmy = User.findByUsername('jimmy') ?: new User(username: 'jimmy', password: 'JimmyKpi123').save()
        def brian = User.findByUsername('brian') ?: new User(username: 'brian', password: 'BrianKpi123').save()
        def user = User.findByUsername('user') ?: new User(username: 'user', password: 'UserKpi123').save()

        UserRole.findOrSaveWhere(user: nick, role: userRole)
        UserRole.findOrSaveWhere(user: jimmy, role: userRole)
        UserRole.findOrSaveWhere(user: brian, role: userRole)
        UserRole.findOrSaveWhere(user: user, role: userRole)

        UserRole.findOrSaveWhere(user: nick, role: adminRole)
        UserRole.findOrSaveWhere(user: jimmy, role: adminRole)
        UserRole.findOrSaveWhere(user: brian, role: adminRole)
    }
    def destroy = {
    }
}
