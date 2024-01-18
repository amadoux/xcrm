import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm, ValidatedBlobField } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IEnterprise } from 'app/shared/model/enterprise.model';
import { getEntities as getEnterprises } from 'app/entities/enterprise/enterprise.reducer';
import { getEntities as getEmployees } from 'app/entities/employee/employee.reducer';
import { IDepartment } from 'app/shared/model/department.model';
import { getEntities as getDepartments } from 'app/entities/department/department.reducer';
import { IJobHistory } from 'app/shared/model/job-history.model';
import { getEntities as getJobHistories } from 'app/entities/job-history/job-history.reducer';
import { IEmployee } from 'app/shared/model/employee.model';
import { Pays } from 'app/shared/model/enumerations/pays.model';
import { TypeEmployed } from 'app/shared/model/enumerations/type-employed.model';
import { Level } from 'app/shared/model/enumerations/level.model';
import { ContractType } from 'app/shared/model/enumerations/contract-type.model';
import { SalaryType } from 'app/shared/model/enumerations/salary-type.model';
import { getEntity, updateEntity, createEntity, reset } from './employee.reducer';

export const EmployeeUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const enterprises = useAppSelector(state => state.enterprise.entities);
  const employees = useAppSelector(state => state.employee.entities);
  const departments = useAppSelector(state => state.department.entities);
  const jobHistories = useAppSelector(state => state.jobHistory.entities);
  const employeeEntity = useAppSelector(state => state.employee.entity);
  const loading = useAppSelector(state => state.employee.loading);
  const updating = useAppSelector(state => state.employee.updating);
  const updateSuccess = useAppSelector(state => state.employee.updateSuccess);
  const paysValues = Object.keys(Pays);
  const typeEmployedValues = Object.keys(TypeEmployed);
  const levelValues = Object.keys(Level);
  const contractTypeValues = Object.keys(ContractType);
  const salaryTypeValues = Object.keys(SalaryType);

  const handleClose = () => {
    navigate('/employee');
  };

  useEffect(() => {
    if (!isNew) {
      dispatch(getEntity(id));
    }

    dispatch(getEnterprises({}));
    dispatch(getEmployees({}));
    dispatch(getDepartments({}));
    dispatch(getJobHistories({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  // eslint-disable-next-line complexity
  const saveEntity = values => {
    if (values.id !== undefined && typeof values.id !== 'number') {
      values.id = Number(values.id);
    }
    values.birthDate = convertDateTimeToServer(values.birthDate);
    values.entryDate = convertDateTimeToServer(values.entryDate);
    values.releaseDate = convertDateTimeToServer(values.releaseDate);
    if (values.coefficient !== undefined && typeof values.coefficient !== 'number') {
      values.coefficient = Number(values.coefficient);
    }
    if (values.monthlyGrossAmount !== undefined && typeof values.monthlyGrossAmount !== 'number') {
      values.monthlyGrossAmount = Number(values.monthlyGrossAmount);
    }
    if (values.commissionAmount !== undefined && typeof values.commissionAmount !== 'number') {
      values.commissionAmount = Number(values.commissionAmount);
    }
    values.hireDate = convertDateTimeToServer(values.hireDate);

    const entity = {
      ...employeeEntity,
      ...values,
      enterprise: enterprises.find(it => it.id.toString() === values.enterprise.toString()),
      manager: employees.find(it => it.id.toString() === values.manager.toString()),
      department: departments.find(it => it.id.toString() === values.department.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {
          birthDate: displayDefaultDateTime(),
          entryDate: displayDefaultDateTime(),
          releaseDate: displayDefaultDateTime(),
          hireDate: displayDefaultDateTime(),
        }
      : {
          nationality: 'CAMEROON',
          typeEmployed: 'MARKETER',
          level: 'A',
          contractType: 'CDD',
          salaryType: 'EXECUTIVE_SALARIED',
          ...employeeEntity,
          birthDate: convertDateTimeFromServer(employeeEntity.birthDate),
          entryDate: convertDateTimeFromServer(employeeEntity.entryDate),
          releaseDate: convertDateTimeFromServer(employeeEntity.releaseDate),
          hireDate: convertDateTimeFromServer(employeeEntity.hireDate),
          enterprise: employeeEntity?.enterprise?.id,
          manager: employeeEntity?.manager?.id,
          department: employeeEntity?.department?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="xcrmApp.employee.home.createOrEditLabel" data-cy="EmployeeCreateUpdateHeading">
            <Translate contentKey="xcrmApp.employee.home.createOrEditLabel">Create or edit a Employee</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="employee-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('xcrmApp.employee.firstName')}
                id="employee-firstName"
                name="firstName"
                data-cy="firstName"
                type="text"
              />
              <ValidatedField
                label={translate('xcrmApp.employee.lastName')}
                id="employee-lastName"
                name="lastName"
                data-cy="lastName"
                type="text"
              />
              <ValidatedField
                label={translate('xcrmApp.employee.email')}
                id="employee-email"
                name="email"
                data-cy="email"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('xcrmApp.employee.phoneNumber')}
                id="employee-phoneNumber"
                name="phoneNumber"
                data-cy="phoneNumber"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('xcrmApp.employee.identityCard')}
                id="employee-identityCard"
                name="identityCard"
                data-cy="identityCard"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('xcrmApp.employee.dateInspiration')}
                id="employee-dateInspiration"
                name="dateInspiration"
                data-cy="dateInspiration"
                type="text"
              />
              <ValidatedField
                label={translate('xcrmApp.employee.nationality')}
                id="employee-nationality"
                name="nationality"
                data-cy="nationality"
                type="select"
              >
                {paysValues.map(pays => (
                  <option value={pays} key={pays}>
                    {translate('xcrmApp.Pays.' + pays)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedBlobField
                label={translate('xcrmApp.employee.uploadIdentityCard')}
                id="employee-uploadIdentityCard"
                name="uploadIdentityCard"
                data-cy="uploadIdentityCard"
                openActionLabel={translate('entity.action.open')}
                validate={{}}
              />
              <ValidatedField
                label={translate('xcrmApp.employee.companyName')}
                id="employee-companyName"
                name="companyName"
                data-cy="companyName"
                type="text"
              />
              <ValidatedField
                label={translate('xcrmApp.employee.typeEmployed')}
                id="employee-typeEmployed"
                name="typeEmployed"
                data-cy="typeEmployed"
                type="select"
              >
                {typeEmployedValues.map(typeEmployed => (
                  <option value={typeEmployed} key={typeEmployed}>
                    {translate('xcrmApp.TypeEmployed.' + typeEmployed)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('xcrmApp.employee.cityAgency')}
                id="employee-cityAgency"
                name="cityAgency"
                data-cy="cityAgency"
                type="text"
              />
              <ValidatedField
                label={translate('xcrmApp.employee.residenceCity')}
                id="employee-residenceCity"
                name="residenceCity"
                data-cy="residenceCity"
                type="text"
              />
              <ValidatedField
                label={translate('xcrmApp.employee.address')}
                id="employee-address"
                name="address"
                data-cy="address"
                type="text"
              />
              <ValidatedField
                label={translate('xcrmApp.employee.socialSecurityNumber')}
                id="employee-socialSecurityNumber"
                name="socialSecurityNumber"
                data-cy="socialSecurityNumber"
                type="text"
              />
              <ValidatedField
                label={translate('xcrmApp.employee.birthDate')}
                id="employee-birthDate"
                name="birthDate"
                data-cy="birthDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('xcrmApp.employee.birthPlace')}
                id="employee-birthPlace"
                name="birthPlace"
                data-cy="birthPlace"
                type="text"
              />
              <ValidatedField
                label={translate('xcrmApp.employee.entryDate')}
                id="employee-entryDate"
                name="entryDate"
                data-cy="entryDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('xcrmApp.employee.releaseDate')}
                id="employee-releaseDate"
                name="releaseDate"
                data-cy="releaseDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('xcrmApp.employee.workstation')}
                id="employee-workstation"
                name="workstation"
                data-cy="workstation"
                type="text"
              />
              <ValidatedField
                label={translate('xcrmApp.employee.descriptionWorkstation')}
                id="employee-descriptionWorkstation"
                name="descriptionWorkstation"
                data-cy="descriptionWorkstation"
                type="text"
              />
              <ValidatedField label={translate('xcrmApp.employee.level')} id="employee-level" name="level" data-cy="level" type="select">
                {levelValues.map(level => (
                  <option value={level} key={level}>
                    {translate('xcrmApp.Level.' + level)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('xcrmApp.employee.coefficient')}
                id="employee-coefficient"
                name="coefficient"
                data-cy="coefficient"
                type="text"
              />
              <ValidatedField
                label={translate('xcrmApp.employee.employedManager')}
                id="employee-employedManager"
                name="employedManager"
                data-cy="employedManager"
                type="text"
              />
              <ValidatedField
                label={translate('xcrmApp.employee.numberHours')}
                id="employee-numberHours"
                name="numberHours"
                data-cy="numberHours"
                type="text"
              />
              <ValidatedField
                label={translate('xcrmApp.employee.averageHourlyCost')}
                id="employee-averageHourlyCost"
                name="averageHourlyCost"
                data-cy="averageHourlyCost"
                type="text"
              />
              <ValidatedField
                label={translate('xcrmApp.employee.monthlyGrossAmount')}
                id="employee-monthlyGrossAmount"
                name="monthlyGrossAmount"
                data-cy="monthlyGrossAmount"
                type="text"
              />
              <ValidatedField
                label={translate('xcrmApp.employee.commissionAmount')}
                id="employee-commissionAmount"
                name="commissionAmount"
                data-cy="commissionAmount"
                type="text"
              />
              <ValidatedField
                label={translate('xcrmApp.employee.contractType')}
                id="employee-contractType"
                name="contractType"
                data-cy="contractType"
                type="select"
              >
                {contractTypeValues.map(contractType => (
                  <option value={contractType} key={contractType}>
                    {translate('xcrmApp.ContractType.' + contractType)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('xcrmApp.employee.salaryType')}
                id="employee-salaryType"
                name="salaryType"
                data-cy="salaryType"
                type="select"
              >
                {salaryTypeValues.map(salaryType => (
                  <option value={salaryType} key={salaryType}>
                    {translate('xcrmApp.SalaryType.' + salaryType)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('xcrmApp.employee.hireDate')}
                id="employee-hireDate"
                name="hireDate"
                data-cy="hireDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                id="employee-enterprise"
                name="enterprise"
                data-cy="enterprise"
                label={translate('xcrmApp.employee.enterprise')}
                type="select"
              >
                <option value="" key="0" />
                {enterprises
                  ? enterprises.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.compagnyName}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="employee-manager"
                name="manager"
                data-cy="manager"
                label={translate('xcrmApp.employee.manager')}
                type="select"
              >
                <option value="" key="0" />
                {employees
                  ? employees.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.email}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="employee-department"
                name="department"
                data-cy="department"
                label={translate('xcrmApp.employee.department')}
                type="select"
              >
                <option value="" key="0" />
                {departments
                  ? departments.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/employee" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default EmployeeUpdate;
