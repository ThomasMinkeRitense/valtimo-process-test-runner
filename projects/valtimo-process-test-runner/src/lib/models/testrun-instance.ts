import {TestrunDefinition} from "./testrun-definition";

interface TestrunInstance {
  id?: string;
  definition: TestrunDefinition;
  documentId: string;
}

export {TestrunInstance}
