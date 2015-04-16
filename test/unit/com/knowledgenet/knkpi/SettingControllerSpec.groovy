package com.knowledgenet.knkpi



import grails.test.mixin.*
import spock.lang.*

@TestFor(SettingController)
@Mock(Setting)
class SettingControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.settingInstanceList
            model.settingInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.settingInstance!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def setting = new Setting()
            setting.validate()
            controller.save(setting)

        then:"The create view is rendered again with the correct model"
            model.settingInstance!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            setting = new Setting(params)

            controller.save(setting)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/setting/show/1'
            controller.flash.message != null
            Setting.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def setting = new Setting(params)
            controller.show(setting)

        then:"A model is populated containing the domain instance"
            model.settingInstance == setting
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def setting = new Setting(params)
            controller.edit(setting)

        then:"A model is populated containing the domain instance"
            model.settingInstance == setting
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/setting/index'
            flash.message != null


        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def setting = new Setting()
            setting.validate()
            controller.update(setting)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.settingInstance == setting

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            setting = new Setting(params).save(flush: true)
            controller.update(setting)

        then:"A redirect is issues to the show action"
            response.redirectedUrl == "/setting/show/$setting.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/setting/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def setting = new Setting(params).save(flush: true)

        then:"It exists"
            Setting.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(setting)

        then:"The instance is deleted"
            Setting.count() == 0
            response.redirectedUrl == '/setting/index'
            flash.message != null
    }
}
