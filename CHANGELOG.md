# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](http://keepachangelog.com/en/1.0.0/) and this project adheres to [Semantic Versioning](http://semver.org/spec/v2.0.0.html).

## [3.0.0-RC3-SNAPSHOT]
### Added
- new Contacts API
- Subusers API

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