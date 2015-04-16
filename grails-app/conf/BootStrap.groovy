import com.knowledgenet.knkpi.Setting

class BootStrap {

    def init = { servletContext ->
        Setting setting = Setting.findOrSaveWhere(auth: 'NLAuth nlauth_account=3388776, nlauth_email=jimmyfrost@gmail.com, nlauth_signature=AbeFroman1, nlauth_role=3',
            baseUrl: 'https://rest.netsuite.com/app/site/hosting/',
            searchesUrl: 'restlet.nl?script=108&deploy=1',
            employeesUrl: 'restlet.nl?script=109&deploy=1').save()
    }
    def destroy = {
    }
}
