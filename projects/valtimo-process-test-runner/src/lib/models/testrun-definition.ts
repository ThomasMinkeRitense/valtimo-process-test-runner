interface TestrunDefinition {
  id?: string;
  title: string;
  process_definition_key: string;
  document_definition_name: string;
  payload: any;
}

type TestrunDefinitionMetadataModalType = 'add' | 'edit';

export {TestrunDefinition, TestrunDefinitionMetadataModalType}
