# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](http://keepachangelog.com/en/1.0.0/) and this project adheres to [Semantic Versioning](http://semver.org/spec/v2.0.0.html).

## [3.0.0-RC3-SNAPSHOT]
### Added
- new Contacts API
- Subusers API
- `pl.smsapi.exception.SmsapiErrorException` to handle API error responses
- `date_sent` to SMS/MMS send action responses
- `time_restriction` parameter for SMS send action
- HLR action factory, `HlrFactory`

### Changed
- `pl.smsapi.api.UserFactory.actionAdd` marked as deprecated, use `pl.smsapi.api.action.subusers.SubusersFactory.actionAdd` instead
- `pl.smsapi.api.UserFactory.actionEdit` marked as deprecated, use `pl.smsapi.api.action.subusers.SubusersFactory.actionEdit` instead
- `pl.smsapi.api.UserFactory.actionList` marked as deprecated, use `pl.smsapi.api.action.subusers.SubusersFactory.actionList` instead
- `pl.smsapi.api.action.user.UserAdd` marked as deprecated, use `pl.smsapi.api.action.subusers.SubuserAdd` instead
- `pl.smsapi.api.action.user.UserEdit` marked as deprecated, use `pl.smsapi.api.action.subusers.SubuserEdit` instead
- `pl.smsapi.api.action.user.UserGet` marked as deprecated, use `pl.smsapi.api.action.subusers.SubuserGet` instead
- `pl.smsapi.api.action.user.UserList` marked as deprecated, use `pl.smsapi.api.action.subusers.SubusersList` instead
- `pl.smsapi.BasicAuthClient` marked as deprecated
- non-proxy action factories marked as deprecated
- `pl.smsapi.api.ContactsFactory` marked as deprecated, use `pl.smsapi.api.action.contacts.ContactsFactory` or `pl.smsapi.api.action.contacts.groups.GroupsFactory` instead
- `pl.smsapi.exception.ActionException` marked as deprecated, use `pl.smsapi.exception.SmsapiLegacyErrorException` instead
- `pl.smsapi.exception.ClientException` marked as deprecated, use `pl.smsapi.exception.SmsapiLegacyErrorException` instead
- `pl.smsapi.exception.HostException` marked as deprecated, use `pl.smsapi.exception.SmsapiLegacyErrorException` instead
- all client and server side errors are now being translated to `pl.smsapi.exception.SmsapiErrorException` or `pl.smsapi.exception.SmsapiLegacyErrorException`
- `pl.smsapi.api.VmsFactory.actionSend` without parameters marked as deprecated
- `pl.smsapi.api.VmsFactory.actionGet` without parameters marked as deprecated
- `pl.smsapi.api.VmsFactory.actionDelete` without parameters marked as deprecated
- `pl.smsapi.api.action.vms.VMSDelete` without parameters marked as deprecated
- `pl.smsapi.api.action.vms.VMSGet` without parameters marked as deprecated
- `pl.smsapi.api.action.vms.VMSSend` without parameters marked as deprecated
- `pl.smsapi.api.SmsFactory.actionSend` without parameters marked as deprecated
- `pl.smsapi.api.action.sms.SMSSend` without parameters marked as deprecated
- `pl.smsapi.api.SmsFactory.actionGet` without parameters marked as deprecated
- `pl.smsapi.api.action.sms.SMSGet` without parameters marked as deprecated
- `pl.smsapi.api.SmsFactory.actionDelete` without parameters marked as deprecated
- `pl.smsapi.api.action.sms.SMSDelete` without parameters marked as deprecated

### Removed
- legacy `phonebook.do` contacts API support

### Removed
- deprecated `pl.smsapi.proxy.Proxy#execute` method

## [3.0.0-RC2] - 2024-03-04
### Changed
- build process

## [3.0.0-RC1] - 2024-03-01
### Changed
- Java 6 support dropped