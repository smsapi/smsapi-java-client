# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](http://keepachangelog.com/en/1.0.0/) and this project adheres to [Semantic Versioning](http://semver.org/spec/v2.0.0.html).

## [Unreleased]

## [3.0.1] - 2024-11-18
### Fixed
- User-Agent header

## [3.0.0] - 2024-10-30

## [3.0.0-RC17] - 2024-10-29
### Changed
- `pl.smsapi.api.action.subusers.SubuserAdd` and `pl.smsapi.api.action.subusers.SubuserEdit` actions setters, fluent interface added

## [3.0.0-RC5 - 3.0.0-RC16] - 2024-09-23
### Changed
- build process

## [3.0.0-RC4]
### Added
- new Sendernames API
- new Contacts API
- Subusers API
- `pl.smsapi.exception.SmsapiErrorException` to handle API error responses
- `date_sent` to SMS/MMS send action responses
- `time_restriction` parameter for SMS send action
- HLR action factory, `HlrFactory`
- HLR with IDX support

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
- `pl.smsapi.api.action.hlr.HLRCheckNumber` without parameters marked as deprecated
- `pl.smsapi.api.action.sender.SenderAdd` marked as deprecated, use `pl.smsapi.api.action.sms.sendernames.SendernameAdd` instead
- `pl.smsapi.api.action.sender.SenderDefault` marked as deprecated, use `pl.smsapi.api.action.sms.sendernames.SendernameMakeDefault` instead
- `pl.smsapi.api.action.sender.SenderDelete` marked as deprecated, use `pl.smsapi.api.action.sms.sendernames.SendernameDelete` instead
- `pl.smsapi.api.action.sender.SenderList` marked as deprecated, use `pl.smsapi.api.action.sms.sendernames.SendernamesList` instead
- `pl.smsapi.api.SenderFactory` marked as deprecated, use `pl.smsapi.api.action.sms.sendernames.SendernamesFactory` instead

### Removed
- legacy `phonebook.do` contacts API support

### Removed
- deprecated `pl.smsapi.proxy.Proxy#execute` method

## [3.0.0-RC2 - 3.0.0-RC3] - 2024-03-04
### Changed
- build process

## [3.0.0-RC1] - 2024-03-01
### Changed
- Java 6 support dropped