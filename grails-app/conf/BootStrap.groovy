import com.knowledgenet.knkpi.Setting

class BootStrap {

    def init = { servletContext ->
        Setting setting = Setting.findOrSaveWhere(auth: 'NLAuth nlauth_account=3388776, nlauth_email=jimmyfrost@gmail.com, nlauth_signature=AbeFroman1, nlauth_role=3',
            baseUrl: 'https://rest.netsuite.com/app/site/hosting/',
            searchesUrl: 'restlet.nl?script=108&deploy=1',
            employeesUrl: 'restlet.nl?script=109&deploy=1',
            revenueSetting: '{A: 15000, B: 10000, C: 8000, D: 5000, F: 3000}',
            callSetting: '{A: 900, B: 800, C: 700, D: 600, F: 500}',
            demoSetting: '{A: 20, B: 15, C: 10, D: 7, F: 5}',
            pipelineSetting: '{A: 100000, B: 80000, C: 60000, D: 40000, F: 30000}',
            closingSetting: '{A: 0.40, B: 0.30, C: 0.25, D: 0.20, F: 0.15}').save()


    }
    def destroy = {
    }
}
